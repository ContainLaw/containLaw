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