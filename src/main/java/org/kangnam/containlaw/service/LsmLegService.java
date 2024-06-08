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
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BillService billService;
    public List<Bill> saveBillList(List<LsmLegRes.LsmLeg> lsmLegList) throws IOException, InterruptedException {
        List<Bill> billList = new ArrayList<>();
        for (LsmLegRes.LsmLeg lsmLegFormRow : lsmLegList) {
            Bill bill = billService.saveBill(lsmLegFormRow);
            billList.add(bill);
        }
        return billList;
    }
    public void updateBillList(List<LsmLegRes.LsmLeg> lsmLegList) {
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

