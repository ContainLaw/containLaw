package org.kangnam.containlaw.service;
import org.kangnam.containlaw.entity.MemberProfile;

import java.util.List;

public interface MemberProfileServiceImpl {
    List<MemberProfile> getAllMemberProfiles();
    List<MemberProfile> searchByName(String name);
    MemberProfile saveMemberProfile(MemberProfile memberProfile);
    MemberProfile updateMemberProfile(Long id, MemberProfile  memberProfile);
}
