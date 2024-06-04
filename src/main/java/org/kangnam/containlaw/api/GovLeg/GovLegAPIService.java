package org.kangnam.containlaw.api.GovLeg;


import org.kangnam.containlaw.Dto.GovLeg.GovLegRes;
import org.kangnam.containlaw.Dto.GovLegReq;
import org.kangnam.containlaw.utils.GovLegStatReqParmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
//@PropertySource("classpath:config.properties")
public class GovLegAPIService implements GovLegAPIServiceImpl { // Government Legislative Status Service

//    @Value("${GOV_LM_API_KEY}")
    private String OC;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public GovLegRes getGovLegContent(GovLegReq govLegReq) {
        String todayFmt = GovLegStatReqParmMapper.getDateForm(LocalDateTime.now());
        govLegReq.setStDtFmt(todayFmt);
        govLegReq.setEdDtFmt(todayFmt);


        String url = makeGovLegStatReqUrl(govLegReq);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
            // XML 파싱 및 처리 로직 추가 필요
        }
        return null;
    }
    private String makeGovLegStatReqUrl(GovLegReq govLegReq) {
        return "http://www.lawmaking.go.kr/rest/govLmSts.xml?OC=" + OC + govLegReq;
    }
}