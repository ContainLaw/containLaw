package org.kangnam.containlaw.Dto.NsmLeg;


import jakarta.persistence.Embeddable;
import lombok.Getter;


@Embeddable
@Getter
public class Proposer {
    String BILL_NO;
    String name;
    String groupName;
    String chineseName;

    public Proposer(String BILL_NO, String name, String groupName, String chineseName) {
        this.BILL_NO = BILL_NO;
        this.name = name;
        this.groupName = groupName;
        this.chineseName = chineseName;
    }
    public Proposer() {}

    @Override
    public String toString() {
        return "제안자{" +
                "이름='" + name + '\'' +
                ", 소속='" + groupName + '\'' +
                "}\n";
    }
}
