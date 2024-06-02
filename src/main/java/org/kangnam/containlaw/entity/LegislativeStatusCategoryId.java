package org.kangnam.containlaw.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class LegislativeStatusCategoryId implements Serializable {

    @Column(name = "bill_id")
    private String billId;
    @Column(name = "category_id")
    private Integer categoryId;

}
