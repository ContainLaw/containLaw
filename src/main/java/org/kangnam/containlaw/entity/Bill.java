package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@Table(name = "bill")
public class Bill {

    @Id
    @Column(name = "bill_id")
    private String billId;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "bill_name")
    private String billName;

    @Column(name = "summary")
    private String Summary;

    @Column(name="category")
    private String category;

    @Column(name="advantages")
    private String advantages;

    @Column(name="disadvantages")
    private String disadvantages;

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

    @Column(name="proposer_list")
    private String proposerList;


    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillMemberProfile> billMemberProfiles = new ArrayList<>();


}

