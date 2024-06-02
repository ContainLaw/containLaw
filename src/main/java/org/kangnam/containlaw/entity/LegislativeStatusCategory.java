package org.kangnam.containlaw.entity;

import jakarta.persistence.*;

@Entity
@IdClass(LegislativeStatusCategoryId.class)
public class LegislativeStatusCategory {

    @Id
    private String billId;

    @Id
    private Integer categoryId;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    // Getters and setters
}

