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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LsmLegService {

    private static final Logger logger = LoggerFactory.getLogger(LsmLegService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LsmLegAPIService lsmLegAPI;

    @Autowired
    private BillService billService;

    //    @Scheduled(cron = "0 0 18 * * *")
    @Scheduled(fixedRate = 100000)
    @Transactional
    public void autoGetLsmLegState() throws IOException, InterruptedException {
        LsmLegReq lsmLegReq = new LsmLegReq();
        lsmLegReq.setPSize("1000");
        List<LsmLegRes.LsmLeg> lsmLegList = lsmLegAPI.getRows(lsmLegReq);

        if (lsmLegList == null || lsmLegList.isEmpty()) {
            logger.warn("No legislative data received from the API");
            return;
        }
        List<Bill> billList = saveBillList(lsmLegList); // 최초 변환 및 저장
        updateBillList(lsmLegList); // 제안자 추가
//        updateBill(billList); // GPT 요약
    }
    private List<Bill> saveBillList(List<LsmLegRes.LsmLeg> lsmLegList) throws IOException, InterruptedException { 
        List<Bill> billList = new ArrayList<>();
        for (LsmLegRes.LsmLeg lsmLegFormRow : lsmLegList) {
            Bill bill = billService.saveBill(lsmLegFormRow);
            billList.add(bill);
            logger.info("법안 ID: {}가 제안자들과 함께 처리되었습니다", lsmLegFormRow.getBillId());
        }
        return billList;
    }
    private void updateBillList(List<LsmLegRes.LsmLeg> lsmLegList) {
        for (LsmLegRes.LsmLeg lsmLeg : lsmLegList) {
            billService.updateBillProposer(lsmLeg);
        }
    }
    private void updateBill(List<Bill> billList) throws IOException, InterruptedException {
        for (Bill bill : billList) {
            billService.updateBill(bill);
        }
    }
}

