package org.kangnam.containlaw.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BillMemberProfileId implements Serializable {

    private String billId;
    private Integer memberProfileId;

}
