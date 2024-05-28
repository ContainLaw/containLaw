package org.kangnam.containlaw.api.NsmLeg;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import java.util.List;

@Data
@XmlRootElement(name = "TVBPMBILL11")
@XmlAccessorType(XmlAccessType.FIELD)
public class LsmLegRes {
    @XmlElement(name = "list_total_count")
    private int listTotalCount;

    @XmlElement(name = "RESULT")
    private ResResult result;

    @XmlElement(name = "row")
    private List<LsmLeg> rows;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name="RESULT")
    public static class ResResult {
        @XmlElement(name = "CODE")
        private String code;

        @XmlElement(name = "MESSAGE")
        private String message;
    }

    @Data
    @XmlRootElement(name = "ROW")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class LsmLeg {
        @XmlElement(name = "BILL_ID")
        private String billId;

        @XmlElement(name = "BILL_NO")
        private String billNo;

        @XmlElement(name = "AGE")
        private String age;

        @XmlElement(name = "BILL_NAME")
        private String billName;

        @XmlElement(name = "PROPOSER")
        private String proposer;

        @XmlElement(name = "PROPOSER_KIND")
        private String proposerKind;

        @XmlElement(name = "PROPOSE_DT")
        private String proposeDt;

        @XmlElement(name = "CURR_COMMITTEE_ID")
        private String currCommitteeId;

        @XmlElement(name = "CURR_COMMITTEE")
        private String currCommittee;

        @XmlElement(name = "COMMITTEE_DT")
        private String committeeDt;

        @XmlElement(name = "COMMITTEE_PROC_DT")
        private String committeeProcDt;

        @XmlElement(name = "LINK_URL")
        private String linkUrl;

        @XmlElement(name = "RST_PROPOSER")
        private String rstProposer;

        @XmlElement(name = "LAW_PROC_RESULT_CD")
        private String lawProcResultCd;

        @XmlElement(name = "LAW_PROC_DT")
        private String lawProcDt;

        @XmlElement(name = "LAW_PRESENT_DT")
        private String lawPresentDt;

        @XmlElement(name = "LAW_SUBMIT_DT")
        private String lawSubmitDt;

        @XmlElement(name = "CMT_PROC_RESULT_CD")
        private String cmtProcResultCd;

        @XmlElement(name = "CMT_PROC_DT")
        private String cmtProcDt;

        @XmlElement(name = "CMT_PRESENT_DT")
        private String cmtPresentDt;

        @XmlElement(name = "RST_MONA_CD")
        private String rstMonaCd;

        @XmlElement(name = "PROC_RESULT_CD")
        private String procResultCd;

        @XmlElement(name = "PROC_DT")
        private String procDt;
    }
}

