package org.kangnam.containlaw.api.NsmLeg.response;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ROW {
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
