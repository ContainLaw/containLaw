package org.kangnam.containlaw.service;

import org.kangnam.containlaw.Dto.MemberProfileDto;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.repository.MemberProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberProfileService {

    @Autowired
    private MemberProfileRepository memberProfileRepository;

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

    public MemberProfileDto saveMemberProfile(MemberProfileDto memberProfileDto) {
        MemberProfile memberProfile = MemberProfile.fromDto(memberProfileDto);
        MemberProfile savedProfile = memberProfileRepository.save(memberProfile);
        return savedProfile.toDto();
    }

    public MemberProfileDto updateMemberProfile(Long id, MemberProfileDto memberProfileDto) {
        Optional<MemberProfile> existingProfile = memberProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            MemberProfile memberProfile = MemberProfile.fromDto(memberProfileDto);
            memberProfile.setId(id);
            MemberProfile updatedProfile = memberProfileRepository.save(memberProfile);
            return updatedProfile.toDto();
        } else {
            return null;
        }
    }
}