package org.kangnam.containlaw.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kangnam.containlaw.api.NsmLeg.Proposer;
import org.kangnam.containlaw.api.NsmLeg.LsmLegReq;
import org.kangnam.containlaw.api.NsmLeg.LsmLegRes;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.kangnam.containlaw.utils.DataTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
//@PropertySource("classpath:config.properties")
public class LsmLegAPIService implements iLsmLegAPIService{
//    @Value("${LSM_LM_API_KEY}")
    private String KEY;
    private final RestTemplate restTemplate;
    public LsmLegAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//    @Scheduled(cron = "0 0 18 * * *")
//    @Scheduled(fixedRate = 100000)
    private void autoGetLsmLegState() {
        LsmLegReq lsmLegReq = new LsmLegReq();
        List<Proposer> proposerList;
        // 국회 입법 현황 헤더목록 가져오기 API 호출
        List<LsmLegRes.LsmLeg> lsmLegList = getRows(lsmLegReq);
        if (lsmLegList == null) return;

        // 국회 입법 헤더 정보로 제안자 목록 크롤링
        for (LsmLegRes.LsmLeg lsmLegFormRow : lsmLegList) {
            proposerList = getProposerList(lsmLegFormRow);
            System.out.println(getLsmLegContent(lsmLegFormRow.getBillId())+"\n"+proposerList);;
        }
        /*
            DB에 저장하는 로직 작성 필요
            주제 및 카테고라기 ???인 -> 구현 예정
            입법번호(lsmLegHeader.getBillId())의 제안자(proponentList)를 DB에 저장
        */
    }

    // 국회 입법 현황 헤더목록 가져오기 API
    // API : https://open.assembly.go.kr/portal/openapi/TVBPMBILL11
    public List<LsmLegRes.LsmLeg> getRows(LsmLegReq lsmLegReq) {

        String url = makeLsmLegHeaderReqUrl(lsmLegReq);
        try {
            String xmlResponse = restTemplate.getForEntity(url, String.class).getBody();
            LsmLegRes.ResResult result = DataTypeConverter.xmlToObject(xmlResponse, LsmLegRes.ResResult.class);

            if (result != null && !result.getCode().equals("INFO-000")) {
                System.out.println(result.getMessage());
                return null;
            }

            LsmLegRes tvbpmbill11 = DataTypeConverter.xmlToObject(xmlResponse, LsmLegRes.class);
            return Objects.requireNonNull(tvbpmbill11).getRows();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 국회 입법 헤더 정보로 제안자 목록 크롤링
    // URL : https://likms.assembly.go.kr/bill/coactorListPopup.do
    public List<Proposer> getProposerList(LsmLegRes.LsmLeg row) {
        String url = makeLsmLegProposerReqUrl(row);
        List<Proposer> proposerList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements aTextList = doc.select("#periodDiv > div.layerInScroll.coaTxtScroll > div a");
            if (aTextList.isEmpty()) { // 제안자가 1명인 경우 국회의원 프로필 바로가기 페이지가 존재하지 않음
                proposerList.add(new Proposer(row.getBillNo(), row.getProposer(),"",""));
            } else {
                for (Element aText : aTextList) {
                    String[] info = aText.text().split("\\(|\\/|\\)");
                    String name = info.length > 0 ? info[0] : "";
                    String group = info.length > 1 ? info[1] : "";
                    String chineseName = info.length > 2 ? info[2] : "";
                    proposerList.add(new Proposer(row.getBillNo(), name, group, chineseName));
                }
            }
            return proposerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 제안이유 및 주요내용 크롤링
    // url : https://likms.assembly.go.kr/bill/billDetail.do
    public String getLsmLegContent(String BILL_ID) {
        String url = createLsmLegContentUrl(BILL_ID);
        System.out.println(url);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.select("#summaryContentDiv");
            return content.text();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public String makeLsmLegHeaderReqUrl(LsmLegReq lsmLegReq) {
        return "https://open.assembly.go.kr/portal/openapi/TVBPMBILL11?Key=" + KEY + lsmLegReq;
    }
    public String makeLsmLegProposerReqUrl(LsmLegRes.LsmLeg row) {
        return "https://likms.assembly.go.kr/bill/coactorListPopup.do?billId=" + row.getBillId();
    }
    public String createLsmLegContentUrl(String BILL_ID) {
        return "https://likms.assembly.go.kr/bill/billDetail.do?billId=" + BILL_ID;
    }
}
