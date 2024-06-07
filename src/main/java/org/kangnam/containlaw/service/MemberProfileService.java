package org.kangnam.containlaw.service;

import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.repository.MemberProfileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberProfileService implements MemberProfileServiceImpl {
    private RestTemplate restTemplate;

    @Autowired
    private MemberProfileRepositoryImpl memberProfileRepository;

    public MemberProfileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MemberProfile> getAllMemberProfiles() {
        return memberProfileRepository.findAll();
    }

    public List<MemberProfile> searchByName(String name) {
        return memberProfileRepository.findByNameContaining(name);
    }


    public List<MemberProfile> searchByPartyName(String partyName){
        return memberProfileRepository.findByPartyNameContaining(partyName);
    }

    public List<MemberProfile> searchByDistrict(String district){
        return memberProfileRepository.findByDistrictContaining(district);
    }

    public MemberProfile saveMemberProfile(MemberProfile memberProfile) {
        return memberProfileRepository.save(memberProfile);
    }

    @Override
    public MemberProfile updateMemberProfile(Long id, MemberProfile memberProfile) {
        MemberProfile existingProfile = memberProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setName(memberProfile.getName());
        existingProfile.setPartyName(memberProfile.getPartyName());
        existingProfile.setDistrict(memberProfile.getDistrict());

        return memberProfileRepository.save(existingProfile);
    }

    public MemberProfile getProfileById(Long id) {
        return memberProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
