package org.kangnam.containlaw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kangnam.containlaw.Dto.MemberProfileDto;
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
    private ObjectMapper objectMapper;

    @Autowired(required = false) // DB 비활성화 bean null
    private MemberProfileRepositoryImpl memberProfileRepository;

    public MemberProfileService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<MemberProfileDto> getAllMemberProfiles() {
        return memberProfileRepository.findAll()
                .stream()
                .map(MemberProfile::toDto)
                .collect(Collectors.toList());
    }

    public List<MemberProfileDto> searchByName(String name) {
        return memberProfileRepository.findByNameContaining(name)
                .stream()
                .map(MemberProfile::toDto)
                .collect(Collectors.toList());
    }

    public List<MemberProfileDto> searchByPartyName(String partyName){
        return memberProfileRepository.findByPartyNameContaining(partyName)
                .stream()
                .map(MemberProfile::toDto)
                .collect(Collectors.toList());
    }

    public List<MemberProfileDto> searchByDistrict(String district){
        return memberProfileRepository.findByDistrictContaining(district)
                .stream()
                .map(MemberProfile::toDto)
                .collect(Collectors.toList());
    }

    public MemberProfileDto saveMemberProfile(MemberProfileDto memberProfileDto) {
        MemberProfile memberProfile = MemberProfile.fromDto(memberProfileDto);
        MemberProfile savedProfile = memberProfileRepository.save(memberProfile);
        return savedProfile.toDto();
    }

    @Override
    public MemberProfileDto updateMemberProfile(Long id, MemberProfileDto memberProfileDto) {
        return null;
    }

    public MemberProfile findByName(String name) {
        return memberProfileRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    public MemberProfile getProfileById(Long id) {
        MemberProfile memberProfile = memberProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return memberProfile;
    }
}