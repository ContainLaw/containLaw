package org.kangnam.containlaw.controller;

import lombok.Getter;
import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.service.BillService;
import org.kangnam.containlaw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private CategoryService categoryService;

    private static final int MAX_RECENT_SEARCHES = 10;

    @Getter
    private List<String> recentBillSearches = new LinkedList<>();

    //법안이름으로 법안 검색
    @GetMapping("/searchByBill")
    public String searchByBillName(@RequestParam String billName, Model model) {
        List<Bill> bills = billService.findByBillName(billName);
        model.addAttribute("bills", bills);
        model.addAttribute("searchTerm", billName);
        addRecentSearch(billName, recentBillSearches);
        model.addAttribute("recentBillSearches", recentBillSearches);
        return "searchByBill";
    }

    //법안 상세보기
    @GetMapping("/bills/{id}")
    public String showBill(@PathVariable String id, Model model) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        model.addAttribute("profiles", bill.getMembers());
        return "bill";
    }

    //카테고리별 법안 검색
    @GetMapping("/category/{category}")
    public String getBillsByCategory(@PathVariable("category") String categoryName, Model model) {
        List<Bill> bills = categoryService.findBillsByCategory(categoryName);
        model.addAttribute("bills", bills);
        model.addAttribute("category", categoryName);
        return "searchByCategory";
    }

    @GetMapping("/deleteRecentBillSearch")
    public String deleteRecentBillSearch(@RequestParam("search") String searchTerm) {
        recentBillSearches.remove(searchTerm);
        return "redirect:/";
    }

    @PostMapping("/clearRecentBillSearch")
    public String clearRecentBillSearch() {
        recentBillSearches.clear();
        return "redirect:/";
    }

    // 최근 검색어 기록 메소드
    private void addRecentSearch(String searchTerm, List<String> recentSearches) {
        recentSearches.remove(searchTerm); // 이미 존재하는 검색어인 경우 기존 위치에서 제거
        recentSearches.add(0, searchTerm); // 새로운 검색어를 리스트의 맨 앞에 추가
        if (recentSearches.size() > MAX_RECENT_SEARCHES) {
            recentSearches.remove(recentSearches.size() - 1); // 최대 검색어 개수를 초과하면 가장 오래된 검색어 제거
        }
    }
}
