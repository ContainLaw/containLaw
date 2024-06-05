package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.kangnam.containlaw.Dto.MemberProfileDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "memberprofile")
public class MemberProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String name;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String hanjaName;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String englishName;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String calendarType;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String birthDate;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String partyName;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String district;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String mainCommittee;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String reelection;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String elected;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String gender;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String phoneNumber;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String officeNumber;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String email;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String website;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String twitter;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String facebook;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String youtube;
    @Column(nullable = false, columnDefinition = "DEFAULT ''")
    private String blog;

    @OneToMany(mappedBy = "memberProfile", cascade = CascadeType.ALL)
    private List<BillMemberProfile> billMemberProfiles = new ArrayList<>();


    public List<Bill> getBills(){
        return billMemberProfiles.stream()
                .map(BillMemberProfile::getBill)
                .toList();
    }
    public MemberProfileDto toDto() {
        MemberProfileDto dto = new MemberProfileDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setHanjaName(this.hanjaName);
        dto.setEnglishName(this.englishName);
        dto.setCalendarType(this.calendarType);
        dto.setBirthDate(this.birthDate);
        dto.setPartyName(this.partyName);
        dto.setDistrict(this.district);
        dto.setMainCommittee(this.mainCommittee);
        dto.setReelection(this.reelection);
        dto.setElected(this.elected);
        dto.setGender(this.gender);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setOfficeNumber(this.officeNumber);
        dto.setEmail(this.email);
        dto.setWebsite(this.website);
        dto.setTwitter(this.twitter);
        dto.setFacebook(this.facebook);
        dto.setYoutube(this.youtube);
        dto.setBlog(this.blog);
        return dto;
    }

    public static MemberProfile fromDto(MemberProfileDto dto) {
        MemberProfile entity = new MemberProfile();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setHanjaName(dto.getHanjaName());
        entity.setEnglishName(dto.getEnglishName());
        entity.setCalendarType(dto.getCalendarType());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPartyName(dto.getPartyName());
        entity.setDistrict(dto.getDistrict());
        entity.setMainCommittee(dto.getMainCommittee());
        entity.setReelection(dto.getReelection());
        entity.setElected(dto.getElected());
        entity.setGender(dto.getGender());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setOfficeNumber(dto.getOfficeNumber());
        entity.setEmail(dto.getEmail());
        entity.setWebsite(dto.getWebsite());
        entity.setTwitter(dto.getTwitter());
        entity.setFacebook(dto.getFacebook());
        entity.setYoutube(dto.getYoutube());
        entity.setBlog(dto.getBlog());
        return entity;
    }
}