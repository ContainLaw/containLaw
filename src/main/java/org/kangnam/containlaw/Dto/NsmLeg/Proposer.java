package org.kangnam.containlaw.Dto.NsmLeg;


import lombok.Getter;

@Getter
public class Proposer {
    String BILL_NO;
    String name;
    String group;
    String chineseName;

    public Proposer(String BILL_NO, String name, String group, String chineseName) {
        this.BILL_NO = BILL_NO;
        this.name = name;
        this.group = group;
        this.chineseName = chineseName;
    }

    @Override
    public String toString() {
        return "제안자{" +
                "이름='" + name + '\'' +
                ", 소속='" + group + '\'' +
                "}\n";
    }
}
