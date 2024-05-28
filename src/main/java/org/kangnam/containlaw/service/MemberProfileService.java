package org.kangnam.containlaw.service;

import org.kangnam.containlaw.Dto.MemberProfileDto;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.repository.MemberProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberProfileService implements iMemberProfileService {
    @Autowired
    private RestTemplate restTemplate;

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

//    @Override
    public void getProfileImg(String name) {
        String url = createProfileImgPath(name);
        String jsonData = restTemplate.getForObject(url, String.class);
        System.out.println(jsonData);

        /*
        윤영이가 이미지를 DB에 저장
        */
    }
    public String createProfileImgPath(String name) {
        return "https://open.assembly.go.kr/portal/assm/search/searchAssmMemberSch.do?unitCd=100021&gubunId=MA&schHgNm="
                + name;
    }
}