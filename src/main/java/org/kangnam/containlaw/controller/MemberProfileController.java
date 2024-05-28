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
public class MemberProfileController {

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

    @GetMapping("/search")
    public String searchMemberProfiles(@RequestParam String name, Model model) {
        List<MemberProfileDto> profiles = memberProfileService.searchByName(name);
        model.addAttribute("profiles", profiles);
        return "search";
    }

    @PostMapping
    public ResponseEntity<MemberProfileDto> addMemberProfile(@RequestBody MemberProfileDto memberProfileDto) {
        MemberProfileDto savedProfile = memberProfileService.saveMemberProfile(memberProfileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberProfileDto> updateMemberProfile(@PathVariable Long id, @RequestBody MemberProfileDto memberProfileDto) {
        MemberProfileDto updatedProfile = memberProfileService.updateMemberProfile(id, memberProfileDto);
        return updatedProfile != null ? ResponseEntity.ok(updatedProfile) : ResponseEntity.notFound().build();
    }
}