package org.kangnam.containlaw.api.GovLeg;

import org.kangnam.containlaw.Dto.GovLeg.GovLegRes;
import org.kangnam.containlaw.Dto.GovLegReq;
import org.kangnam.containlaw.api.common.AbstractApiService;
import org.kangnam.containlaw.api.common.ApiParsers;
import org.kangnam.containlaw.utils.GovLegStatReqParmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

@Service
public class GovLegAPIService extends AbstractApiService { // Government Legislative Status Service

    @Value("${external.api.gov-lm-key}")
    private String ocKey;

    @Autowired
    public GovLegAPIService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public GovLegRes getGovLegContent(GovLegReq govLegReq) {
        String todayFmt = GovLegStatReqParmMapper.getDateForm(LocalDateTime.now());
        govLegReq.setStDtFmt(todayFmt);
        govLegReq.setEdDtFmt(todayFmt);

        String url = makeGovLegStatReqUrl(govLegReq);
        return getAndParse(url, ApiParsers.xml(GovLegRes.class, this::parseGovLegResponse));
    }

    private GovLegRes parseGovLegResponse(GovLegRes response) {
        if (response == null) {
            throw new IllegalStateException("GovLeg response is null");
        }

        GovLegRes.Result result = response.getResult();
        if (result != null && !"INFO-000".equals(result.getCode())) {
            logger.warn("GovLeg API returned non-success code={}, message={}", result.getCode(), result.getMessage());
            response.setRows(Collections.emptyList());
            return response;
        }

        response.setRows(Objects.requireNonNullElse(response.getRows(), Collections.emptyList()));
        return response;
    }

    private String makeGovLegStatReqUrl(GovLegReq govLegReq) {
        return "http://www.lawmaking.go.kr/rest/govLmSts.xml?OC=" + ocKey + govLegReq;
    }
}
