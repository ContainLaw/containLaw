package org.kangnam.containlaw.api.GovLeg;

import org.kangnam.containlaw.Dto.GovLeg.GovLegRes;
import org.kangnam.containlaw.Dto.GovLegReq;

public interface GovLegAPIServiceImpl {
    GovLegRes getGovLegContent(GovLegReq govLegReq);
}
