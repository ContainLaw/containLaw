package org.kangnam.containlaw.api.GovLeg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@PropertySource("classpath:config.properties")
public class GovLegService { // Government Legislative Status Service

    private static final Logger logger = LoggerFactory.getLogger(GovLegService.class);

//    @Value("${GOV_LM_API_KEY}")
    private String OC;

    public final RestTemplate restTemplate;

    public GovLegService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    @Scheduled(fixedRate = 3000)
//    @Scheduled(cron="0 0 18 * * *")
    public void autoGetGovLegState() {
        GovLegReq govLegReq = new GovLegReq();
//        govLegReq.setLsKndCd(GovLegStatReqParmMapper.getLsKndCode("교육부"));
        String todayFmt = GovLegStatReqParmMapper.getDateForm(LocalDateTime.now());
        govLegReq.setStDtFmt(todayFmt);
        govLegReq.setEdDtFmt(todayFmt);
        String url = makeGovLegStatReqUrl(govLegReq);
        logger.info("Request URL: {}", url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            // XML 파싱 및 처리 로직 추가 필요
            logger.info("Response: {}", responseBody);
        } else {
            logger.error("Failed to fetch data. Status code: {}", response.getStatusCode());
        }
    }

    private String makeGovLegStatReqUrl(GovLegReq govLegReq) {
        return "http://www.lawmaking.go.kr/rest/govLmSts.xml?OC=" + OC + govLegReq;
    }
}