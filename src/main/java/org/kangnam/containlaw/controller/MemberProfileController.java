package org.kangnam.containlaw.controller;


import org.kangnam.containlaw.Dto.MemberProfileDto;
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
        return "search";
    }

    @GetMapping("/searchByPartyName")
    public String searchPartyNameProfiles(@RequestParam String partyName, Model model) {
        List<MemberProfileDto> profiles = memberProfileService.searchByName(partyName);
        model.addAttribute("profiles", profiles);
        return "search";
    }

    @GetMapping("/searchByDistrict")
    public String searchDistrictProfiles(@RequestParam String district, Model model) {
        List<MemberProfileDto> profiles = memberProfileService.searchByName(district);
        model.addAttribute("profiles", profiles);
        return "search";
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