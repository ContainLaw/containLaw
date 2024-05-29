package org.kangnam.containlaw.service;

import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegReq;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.api.LsmLeg.LsmLegAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LsmLegService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LsmLegAPIService lsmLegAPI;

//    @Scheduled(cron = "0 0 18 * * *")
    @Scheduled(fixedRate = 100000)
    private void autoGetLsmLegState() {
        LsmLegReq lsmLegReq = new LsmLegReq();
        List<Proposer> proposerList;
        // 국회 입법 현황 헤더목록 가져오기 API 호출
        List<LsmLegRes.LsmLeg> lsmLegList = lsmLegAPI.getRows(lsmLegReq);
        if (lsmLegList == null) return;

        // 국회 입법 헤더 정보로 제안자 목록 크롤링
        for (LsmLegRes.LsmLeg lsmLegFormRow : lsmLegList) {
            proposerList = lsmLegAPI.getProposerList(lsmLegFormRow);
            System.out.println(lsmLegAPI.getLsmLegContent(lsmLegFormRow.getBillId())+"\n"+proposerList);;
        }
        /*
            DB에 저장하는 로직 작성 필요
            주제 및 카테고라기 ???인 -> 구현 예정
            입법번호(lsmLegHeader.getBillId())의 제안자(proponentList)를 DB에 저장
        */
    }
}
