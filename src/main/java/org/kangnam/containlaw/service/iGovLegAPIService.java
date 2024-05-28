package org.kangnam.containlaw.service;

import org.kangnam.containlaw.api.GovLeg.GovLegReq;

public interface iGovLegAPIService {
    void autoGetGovLegState();
    String makeGovLegStatReqUrl(GovLegReq govLegReq);
}
