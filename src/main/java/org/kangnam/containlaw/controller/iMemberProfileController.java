package org.kangnam.containlaw.controller;

import org.kangnam.containlaw.Dto.MemberProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface iMemberProfileController {
    List<MemberProfileDto> getAllMemberProfiles();
    String home();
    String searchMemberProfiles(@RequestParam String name, Model model);
    ResponseEntity<MemberProfileDto> addMemberProfile(@RequestBody MemberProfileDto memberProfileDto);
    ResponseEntity<MemberProfileDto> updateMemberProfile(@PathVariable Long id, @RequestBody MemberProfileDto memberProfileDto);
}
