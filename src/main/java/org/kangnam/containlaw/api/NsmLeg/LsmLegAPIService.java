package org.kangnam.containlaw.api.NsmLeg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kangnam.containlaw.api.NsmLeg.response.RESULT;
import org.kangnam.containlaw.api.NsmLeg.response.ROW;
import org.kangnam.containlaw.api.NsmLeg.response.TVBPMBILL11;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.kangnam.containlaw.utils.DataTypeConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@PropertySource("classpath:config.properties")
public class LsmLegAPIService {
    @Value("${LSM_LM_API_KEY}")
    private String KEY;
    private final RestTemplate restTemplate;
    public LsmLegAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//    @Scheduled(cron = "0 0 18 * * *")
    @Scheduled(fixedRate = 100000)
    private void autoGetLsmLegState() {
        LsmLegReq lsmLegReq = new LsmLegReq();
        List<Proposer> proposerList = new ArrayList<>();

        // 국회 입법 현황 헤더목록 가져오기 API 호출
        List<ROW> rows = getRows(lsmLegReq);
        if (rows == null) return;

        // 국회 입법 헤더 정보로 제안자 목록 크롤링
        for (ROW row : rows) {
            proposerList = getProposerList(row);
            System.out.println(row.getBillName() + proposerList);
        }
        /*
            DB에 저장하는 로직 작성 필요
            주제 및 카테고라기 ???인 -> 구현 예정
            입법번호(lsmLegHeader.getBillId())의 제안자(proponentList)를 DB에 저장
        */
    }

    // 국회 입법 현황 헤더목록 가져오기 API
    // API : https://open.assembly.go.kr/portal/openapi/TVBPMBILL11
    private List<ROW> getRows(LsmLegReq lsmLegReq) {

        String url = makeLsmLegHeaderReqUrl(lsmLegReq);
        try {
            String xmlResponse = restTemplate.getForEntity(url, String.class).getBody();
            RESULT result = DataTypeConverter.xmlToObject(xmlResponse, RESULT.class);

            if (result != null && !result.getCode().equals("INFO-000")) {
                System.out.println(result.getMessage());
                return null;
            }

            TVBPMBILL11 tvbpmbill11 = DataTypeConverter.xmlToObject(xmlResponse, TVBPMBILL11.class);
            return Objects.requireNonNull(tvbpmbill11).getRows();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 국회 입법 헤더 정보로 제안자 목록 크롤링
    // URL : https://likms.assembly.go.kr/bill/coactorListPopup.do
    private List<Proposer> getProposerList(ROW row) {
        String url = makeLsmLegProposerReqUrl(row);
        List<Proposer> proposerList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements aTextList = doc.select("#periodDiv > div.layerInScroll.coaTxtScroll > div a");
            if (aTextList.isEmpty()) { // 제안자가 1명인 경우 국회의원 프로필 바로가기 페이지가 존재하지 않음
                proposerList.add(new Proposer(row.getBillId(), row.getProposer(),"",""));
            } else {
                for (Element aText : aTextList) {
                    String[] info = aText.text().split("\\(|\\/|\\)");
                    String name = info.length > 0 ? info[0] : "";
                    String group = info.length > 1 ? info[1] : "";
                    String chineseName = info.length > 2 ? info[2] : "";
                    proposerList.add(new Proposer(row.getBillId(), name, group, chineseName));
                }
            }
            return proposerList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String makeLsmLegHeaderReqUrl(LsmLegReq lsmLegReq) {
        return "https://open.assembly.go.kr/portal/openapi/TVBPMBILL11?Key=" + KEY + lsmLegReq;
    }
    private String makeLsmLegProposerReqUrl(ROW row) {
        return "https://likms.assembly.go.kr/bill/coactorListPopup.do?billId=" + row.getBillId();
    }


}
