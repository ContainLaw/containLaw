package org.kangnam.containlaw.Dto.GovLeg;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "govLmSts")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovLegRes {
    @XmlElement(name = "list_total_count")
    private Integer listTotalCount;

    @XmlElement(name = "RESULT")
    private Result result;

    @XmlElement(name = "row")
    private List<Row> rows = new ArrayList<>();

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Result {
        @XmlElement(name = "CODE")
        private String code;

        @XmlElement(name = "MESSAGE")
        private String message;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Row {
        @XmlElement(name = "LS_ID")
        private String lsId;

        @XmlElement(name = "LS_NM_KO")
        private String lsNmKo;

        @XmlElement(name = "LS_KND_CD")
        private String lsKndCd;

        @XmlElement(name = "LS_KND_NM")
        private String lsKndNm;

        @XmlElement(name = "CPT_OFI_ORG_NM")
        private String cptOfiOrgNm;

        @XmlElement(name = "LB_PRC_STS_NM")
        private String lbPrcStsNm;

        @XmlElement(name = "PROPOS_DT")
        private String proposDt;

        @XmlElement(name = "PROMUL_DT")
        private String promulDt;
    }
}
