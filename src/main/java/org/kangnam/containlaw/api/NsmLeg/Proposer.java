package org.kangnam.containlaw.api.NsmLeg;


import lombok.Getter;

@Getter
public class Proposer {
    String BILL_ID;
    String name;
    String group;
    String chineseName;

    public Proposer(String BILL_ID, String name, String group, String chineseName) {
        this.BILL_ID = BILL_ID;
        this.name = name;
        this.group = group;
        this.chineseName = chineseName;
    }

    @Override
    public String toString() {
        return "Proponent{" +
                "BILL_ID='" + BILL_ID + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", chineseName='" + chineseName + '\'' +
                '}';
    }
}
