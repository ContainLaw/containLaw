package org.kangnam.containlaw.service;

import org.kangnam.containlaw.api.NsmLeg.LsmLegReq;
import org.kangnam.containlaw.api.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.api.NsmLeg.Proposer;
import java.util.List;

public interface iLsmLegAPIService {
    // 국회 입법 현황 헤더목록 가져오기 API
    // API : https://open.assembly.go.kr/portal/openapi/TVBPMBILL11
    public List<LsmLegRes.LsmLeg> getRows(LsmLegReq lsmLegReq);

    // 국회 입법 헤더 정보로 제안자 목록 크롤링
    // URL : https://likms.assembly.go.kr/bill/coactorListPopup.do
    List<Proposer> getProposerList(LsmLegRes.LsmLeg row);

    // 제안이유 및 주요내용 크롤링
    // url : https://likms.assembly.go.kr/bill/billDetail.do
    String getLsmLegContent(String BILL_ID);
    String makeLsmLegHeaderReqUrl(LsmLegReq lsmLegReq);
    String makeLsmLegProposerReqUrl(LsmLegRes.LsmLeg row);
    String createLsmLegContentUrl(String BILL_ID);

}
