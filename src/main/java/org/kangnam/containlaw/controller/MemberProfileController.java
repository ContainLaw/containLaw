package org.kangnam.containlaw.controller;


import org.kangnam.containlaw.Dto.MemberProfileDto;
import org.kangnam.containlaw.api.Profile.ProfileService;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberProfileController implements MemberProfileControllerImpl {

    @Autowired
    private MemberProfileService memberProfileService;


    @GetMapping
    public List<MemberProfileDto> getAllMemberProfiles() {
        return memberProfileService.getAllMemberProfiles();
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/searchByName")
    public String searchMemberProfiles(@RequestParam String name, Model model) {
        List<MemberProfileDto> profiles = memberProfileService.searchByName(name);
        model.addAttribute("profiles", profiles);
        model.addAttribute("searchTerm", name);
        return "searchByName";
    }

    @GetMapping("/searchByPartyName")
    public String searchPartyNameProfiles(@RequestParam String partyName, Model model) {
        List<MemberProfileDto> profiles = memberProfileService.searchByPartyName(partyName);
        model.addAttribute("profiles", profiles);
        model.addAttribute("searchTerm", partyName);
        return "searchByPartyName";
    }

    @GetMapping("/searchByDistrict")
    public String searchDistrictProfiles(@RequestParam String district, Model model) {
        List<MemberProfileDto> profiles = memberProfileService.searchByDistrict(district);
        model.addAttribute("profiles", profiles);
        model.addAttribute("searchTerm", district);
        return "searchByDistrict";
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        MemberProfile profile = memberProfileService.getProfileById(id);
        model.addAttribute("profile", profile);
        return "profile";
    }


    @PostMapping
    public ResponseEntity<MemberProfileDto> addMemberProfile(@RequestBody MemberProfileDto memberProfileDto) {
        MemberProfileDto savedProfile = memberProfileService.saveMemberProfile(memberProfileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @Override
    public ResponseEntity<MemberProfileDto> updateMemberProfile(Long id, MemberProfileDto memberProfileDto) {
        return null;
    }
}