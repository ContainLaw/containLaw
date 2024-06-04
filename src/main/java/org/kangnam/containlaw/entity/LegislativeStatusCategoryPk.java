package org.kangnam.containlaw.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class LegislativeStatusCategoryPk implements Serializable {

    private String billId;
    private Integer categoryId;


    public LegislativeStatusCategoryPk() {}

    public LegislativeStatusCategoryPk(String billId, Integer categoryId) {
        this.billId = billId;
        this.categoryId = categoryId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegislativeStatusCategoryPk that = (LegislativeStatusCategoryPk) o;
        return Objects.equals(billId, that.billId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, categoryId);
    }
}
