package org.kangnam.containlaw.entity;


import jakarta.persistence.*;

@Entity
@IdClass(BillMemberProfileId.class)
public class BillMemberProfile {
    @Id
    private String billId;

    @Id
    private Integer memberProfileId;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @MapsId("memberProfileId")
    @JoinColumn(name = "member_profile_id")
    private MemberProfile memberProfile;
    @Id
    private Long id;

}
