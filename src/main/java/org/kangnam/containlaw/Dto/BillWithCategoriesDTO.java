package org.kangnam.containlaw.Dto;

import lombok.Data;
import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.Category;

import java.util.Set;

@Data
public class BillWithCategoriesDTO {
    private String billId;
    private Set<Category> categories;


    public BillWithCategoriesDTO(Bill bill, Set<Category> categories) {
        this.billId = bill.getBillId();
        this.categories = categories;
    }
}
