package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Setter
@Table(name = "bill")
public class Bill {

    @Id
    @Column(name = "bill_id")
    private String billId;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "bill_name")
    private String billName;

    @Column(name = "proposer")
    private String proposer;

    @Column(name = "committee")
    private String committee;

    @Column(name = "propose_date")
    private LocalDate proposeDate;

    @Column(name = "proc_date")
    private LocalDate procDate;

    @Column(name = "proc_result_code")
    private String procResultCode;

    @Column(name = "url")
    private String url;

    @ManyToMany
    @JoinTable(
            name = "Bill_MemberProfile",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "member_profile_id")
    )
    private List<MemberProfile> members;

    @ManyToMany
    @JoinTable(
            name = "LegislativeStatusCategory",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
