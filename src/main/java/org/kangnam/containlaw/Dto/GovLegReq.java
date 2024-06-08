package org.kangnam.containlaw.Dto;

import lombok.Setter;

@Setter
public class GovLegReq {
    String lsKndCd = "";
    String cptOfiOrgCd = "";
    String stDtFmt = "";
    String edDtFmt = "";
    String lbPrcStsCdGrp = "";
    String lsNmKo = "";

    @Override
    public String toString() {
        return String.format("&lsKndCd=%s&cptOfiOrgCd=%s&stDtFmt=%s&edDtFmt=%s&lbPrcStsCdGrp=%s&lsNmKo=%s",
                lsKndCd, cptOfiOrgCd, stDtFmt, edDtFmt, lbPrcStsCdGrp, lsNmKo);
    }
}