package org.kangnam.containlaw.controller;


import lombok.extern.slf4j.Slf4j;
import org.kangnam.containlaw.Dto.BillWithCategoriesDTO;
import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.Category;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("")
public class MemberProfileController implements MemberProfileControllerImpl {

    @Autowired
    private MemberProfileService memberProfileService;

    @Autowired
    private BillController billController;


    @Override
    public List<MemberProfile> getAllMemberProfiles() {
        return List.of();
    }

    private static final int MAX_RECENT_SEARCHES = 10;
    private List<String> recentNameSearches = new LinkedList<>();
    private List<String> recentPartySearches = new LinkedList<>();
    private List<String> recentDistrictSearches = new LinkedList<>();

    //홈
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("recentBillSearches", billController.getRecentBillSearches());

        model.addAttribute("recentNameSearches", recentNameSearches);
        model.addAttribute("recentPartySearches", recentPartySearches);
        model.addAttribute("recentDistrictSearches", recentDistrictSearches);
        return "home";
    }

    //이름으로 국회의원 검색
    @GetMapping("/searchByName")
    public String searchMemberProfiles(@RequestParam String name, Model model) {
        List<MemberProfile> profiles = memberProfileService.searchByName(name);
        model.addAttribute("profiles", profiles);
        model.addAttribute("searchTerm", name);
        addRecentSearch(name, recentNameSearches);
        return "searchByName";
    }

    //정당으로 국회의원 검색
    @GetMapping("/searchByPartyName")
    public String searchPartyNameProfiles(@RequestParam String partyName, Model model) {
        List<MemberProfile> profiles = memberProfileService.searchByPartyName(partyName);
        model.addAttribute("profiles", profiles);
        model.addAttribute("searchTerm", partyName);
        addRecentSearch(partyName, recentPartySearches);
        return "searchByPartyName";
    }

    //지역으로 국회의원 검색
    @GetMapping("/searchByDistrict")
    public String searchDistrictProfiles(@RequestParam String district, Model model) {
        List<MemberProfile> profiles = memberProfileService.searchByDistrict(district);
        model.addAttribute("profiles", profiles);
        model.addAttribute("searchTerm", district);
        addRecentSearch(district, recentDistrictSearches);
        return "searchByDistrict";
    }

    //개인 프로필
    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        MemberProfile profile = memberProfileService.getProfileById(id);
        model.addAttribute("profile", profile);

        List<Bill> bills = profile.getBills();
        List<BillWithCategoriesDTO> billsWithCategories = new ArrayList<>();

        for (Bill bill : bills) {
            Set<Category> categories = new HashSet<>(bill.getCategories());
            BillWithCategoriesDTO dto = new BillWithCategoriesDTO(bill, categories);
            billsWithCategories.add(dto);
        }

        model.addAttribute("billsWithCategories", billsWithCategories);
        return "profile";
    }

    //국회의원과 카테고리로 법안 검색
    @GetMapping("/profile/{id}/category/{categoryName}")
    public String getBillsByMemberAndCategory(@PathVariable Long id, @PathVariable String categoryName, Model model) {
        MemberProfile profile = memberProfileService.getProfileById(id);
        model.addAttribute("profile", profile);

        List<Bill> bills = profile.getBills();
        List<Bill> filteredBills = new ArrayList<>();

        for (Bill bill : bills) {
            for (Category category : bill.getCategories()) {
                if (category.getName().equals(categoryName)) {
                    filteredBills.add(bill);
                    break;
                }
            }
        }

        model.addAttribute("bills", filteredBills);
        model.addAttribute("category", categoryName);
        return "searchByMemberAndCategory";
    }

    @PostMapping
    public ResponseEntity<MemberProfile> addMemberProfile(@RequestBody MemberProfile memberProfile) {
        MemberProfile savedProfile = memberProfileService.saveMemberProfile(memberProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @Override
    public ResponseEntity<MemberProfile> updateMemberProfile(Long id, MemberProfile memberProfile) {
        return null;
    }

    @PostMapping("/clearRecentSearches")
    public String clearRecentSearches(@RequestParam("type") String type) {
        switch (type) {
            case "name":
                recentNameSearches.clear();
                break;
            case "partyName":
                recentPartySearches.clear();
                break;
            case "district":
                recentDistrictSearches.clear();
                break;
        }
        return "redirect:/";
    }

    @GetMapping("/deleteRecentSearch")
    public String deleteRecentSearch(@RequestParam("search") String searchTerm, @RequestParam("type") String type) {
        switch (type) {
            case "name":
                recentNameSearches.remove(searchTerm);
                break;
            case "partyName":
                recentPartySearches.remove(searchTerm);
                break;
            case "district":
                recentDistrictSearches.remove(searchTerm);
                break;
        }
        return "redirect:/";
    }

    private void addRecentSearch(String searchTerm, List<String> recentSearches) {
        recentSearches.remove(searchTerm); // Remove if it already exists to re-add it at the top
        recentSearches.add(0, searchTerm); // Add to the front of the list
        if (recentSearches.size() >     MAX_RECENT_SEARCHES) {
            recentSearches.remove(recentSearches.size() - 1); // Remove the oldest search if the list is too long
        }
    }
}