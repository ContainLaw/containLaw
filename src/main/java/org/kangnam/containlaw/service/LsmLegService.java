package org.kangnam.containlaw.service;

import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
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

    @Autowired
    private ProposerService proposerService;

    //    @Scheduled(cron = "0 0 18 * * *")
    @Scheduled(fixedRate = 100000)
    @Transactional
    public void autoGetLsmLegState() {
        LsmLegReq lsmLegReq = new LsmLegReq();
        lsmLegReq.setPSize("");
        List<LsmLegRes.LsmLeg> lsmLegList = lsmLegAPI.getRows(lsmLegReq);

        if (lsmLegList == null || lsmLegList.isEmpty()) {
            logger.warn("No legislative data received from the API");
            return;
        }

        for (LsmLegRes.LsmLeg lsmLegFormRow : lsmLegList) {
            Bill bill = billService.saveBill(lsmLegFormRow);
            List<Proposer> proposerList = lsmLegAPI.getProposerList(lsmLegFormRow);
            proposerService.proposeBill(bill);
            logger.info("법안 ID: {}가 제안자들과 함께 처리되었습니다", lsmLegFormRow.getBillId());
        }
    }
}
