package org.kangnam.containlaw.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberProfileDto {
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
}
