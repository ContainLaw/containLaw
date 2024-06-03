package org.kangnam.containlaw.entity;

import jakarta.persistence.*;

@Table(name = "legislative_status_category")
@Entity
@IdClass(LegislativeStatusCategoryPk.class)
public class LegislativeStatusCategory {

    @Id
    @Column(name = "bill_id")
    private String billId;

    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

}
