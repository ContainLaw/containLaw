package org.kangnam.containlaw.controller;

import org.kangnam.containlaw.entity.MemberProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MemberProfileControllerImpl {
    List<MemberProfile> getAllMemberProfiles();
    String home(Model model);
    String searchMemberProfiles(@RequestParam String name, Model model);
    ResponseEntity<MemberProfile> addMemberProfile(@RequestBody MemberProfile memberProfile);
    ResponseEntity<MemberProfile> updateMemberProfile(@PathVariable Long id, @RequestBody MemberProfile memberProfile);
}
