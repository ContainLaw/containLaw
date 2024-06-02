package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.kangnam.containlaw.Dto.MemberProfileDto;

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
    private String name;
    private String hanjaName;
    private String englishName;
    private String calendarType;
    private String birthDate;
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

    @OneToMany(mappedBy = "memberProfile")
    private List<Bill> bills;

    public MemberProfileDto toDto() {
        MemberProfileDto Dto = new MemberProfileDto();
        Dto.setId(this.id);
        Dto.setName(this.name);
        Dto.setHanjaName(this.hanjaName);
        Dto.setEnglishName(this.englishName);
        Dto.setCalendarType(this.calendarType);
        Dto.setBirthDate(this.birthDate);
        Dto.setPartyName(this.partyName);
        Dto.setDistrict(this.district);
        Dto.setMainCommittee(this.mainCommittee);
        Dto.setReelection(this.reelection);
        Dto.setElected(this.elected);
        Dto.setGender(this.gender);
        Dto.setPhoneNumber(this.phoneNumber);
        Dto.setOfficeNumber(this.officeNumber);
        Dto.setEmail(this.email);
        Dto.setWebsite(this.website);
        Dto.setTwitter(this.twitter);
        Dto.setFacebook(this.facebook);
        Dto.setYoutube(this.youtube);
        Dto.setBlog(this.blog);
        return Dto;
    }

    public static MemberProfile fromDto(MemberProfileDto Dto) {
        MemberProfile entity = new MemberProfile();
        entity.setId(Dto.getId());
        entity.setName(Dto.getName());
        entity.setHanjaName(Dto.getHanjaName());
        entity.setEnglishName(Dto.getEnglishName());
        entity.setCalendarType(Dto.getCalendarType());
        entity.setBirthDate(Dto.getBirthDate());
        entity.setPartyName(Dto.getPartyName());
        entity.setDistrict(Dto.getDistrict());
        entity.setMainCommittee(Dto.getMainCommittee());
        entity.setReelection(Dto.getReelection());
        entity.setElected(Dto.getElected());
        entity.setGender(Dto.getGender());
        entity.setPhoneNumber(Dto.getPhoneNumber());
        entity.setOfficeNumber(Dto.getOfficeNumber());
        entity.setEmail(Dto.getEmail());
        entity.setWebsite(Dto.getWebsite());
        entity.setTwitter(Dto.getTwitter());
        entity.setFacebook(Dto.getFacebook());
        entity.setYoutube(Dto.getYoutube());
        entity.setBlog(Dto.getBlog());
        return entity;
    }
}