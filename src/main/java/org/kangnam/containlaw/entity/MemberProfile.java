package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Data
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
    @Column(name = "image_url")
    private String imageUrl;


    @OneToMany(mappedBy = "memberProfile", cascade = CascadeType.ALL)
    private List<BillMemberProfile> billMemberProfiles = new ArrayList<>();


    public List<Bill> getBills() {
        return billMemberProfiles.stream()
                .map(BillMemberProfile::getBill)
                .toList();
    }
}