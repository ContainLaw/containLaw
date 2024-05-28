package org.kangnam.containlaw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.kangnam.containlaw.Dto.MemberProfileDto;

import java.util.Date;
@Getter
@Setter
@Entity
public class MemberProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String hanjaName;
    private String englishName;
    private String calendarType;
    private Date birthDate;
    private String partyName;
    private String district;
    private String mainCommittee;
    private String reelection;
    private String elected;
    private String gender;
    private String phoneNumber;
    private String officeNumber;
    private String email;
    private String website;
    private String twitter;
    private String facebook;
    private String youtube;
    private String blog;


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