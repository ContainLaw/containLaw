package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.kangnam.containlaw.Dto.NsmLeg.Proposer;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @ElementCollection
    @CollectionTable(name = "proposer_names", joinColumns = @JoinColumn(name = "bill_id"))
    private List<Proposer> proposerList = new ArrayList<>(); // Proposer 객체 리스트


    @ManyToMany
    @JoinTable(
            name = "legislative_status_category",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    private List<Category> categories = new ArrayList<>();

}

