package org.kangnam.containlaw.api.GovLeg;

import org.kangnam.containlaw.Dto.GovLeg.GovLegRes;
import org.kangnam.containlaw.Dto.GovLegReq;

public interface iGovLegAPIService {
    GovLegRes getGovLegContent(GovLegReq govLegReq);
}
