package org.kangnam.containlaw.service;

import org.kangnam.containlaw.Dto.NsmLeg.LsmLegReq;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.api.LsmLeg.LsmLegAPIService;
import org.kangnam.containlaw.entity.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScheduledService {
    @Autowired
    private LsmLegAPIService lsmLegAPI;
    @Autowired
    private LsmLegService lsmLegService;
    @Autowired
    private BillService billService;
    private static final Logger logger = LoggerFactory.getLogger(LsmLegService.class);

    @Scheduled(cron = "0 0 18 * * *")
    public void autoUpdateNewLsmLeg() throws IOException, InterruptedException {
        List<LsmLegRes.LsmLeg> lsmLegList = getLsmLegState("3");
        if (lsmLegList != null) {
            List<Bill> billList = lsmLegService.saveBillList(lsmLegList); // 최초 변환 및 저장
            lsmLegService.updateBillList(lsmLegList); // 제안자 추가
            for (Bill bill : billList) {
                billService.updateBill(bill); // GPT 요약
            }
        }

    }

    private List<LsmLegRes.LsmLeg> getLsmLegState(String size){
        try {
            LsmLegReq lsmLegReq = new LsmLegReq();
            lsmLegReq.setPSize(size);
            List<LsmLegRes.LsmLeg> lsmLegList = lsmLegAPI.getRows(lsmLegReq);
            return lsmLegList;
        } catch (Exception e) {
            logger.info("입법 헤더 정보 가져오기 실패 ㅜ,ㅜ");
            return null;
        }
    }
}
