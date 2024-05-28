package org.kangnam.containlaw.service;

import org.kangnam.containlaw.Dto.MemberProfileDto;

import java.util.List;

public interface iMemberProfileService {
    List<MemberProfileDto> getAllMemberProfiles();
    List<MemberProfileDto> searchByName(String name);
    MemberProfileDto saveMemberProfile(MemberProfileDto memberProfileDto);
    MemberProfileDto updateMemberProfile(Long id, MemberProfileDto memberProfileDto);
}
