package org.kangnam.containlaw.api.LsmLeg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegReq;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
import org.kangnam.containlaw.utils.DataTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
//@PropertySource("classpath:config.properties")
public class LsmLegAPIService implements iLsmLegAPIService {
//    @Value("${LSM_LM_API_KEY}")
    private String KEY;

    @Autowired
    private RestTemplate restTemplate;
    @Override
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

    @Override
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

    @Override
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
    private String makeLsmLegHeaderReqUrl(LsmLegReq lsmLegReq) {
        return "https://open.assembly.go.kr/portal/openapi/TVBPMBILL11?Key=" + KEY + lsmLegReq;
    }
    private String makeLsmLegProposerReqUrl(LsmLegRes.LsmLeg row) {
        return "https://likms.assembly.go.kr/bill/coactorListPopup.do?billId=" + row.getBillId();
    }
    private String createLsmLegContentUrl(String BILL_ID) {
        return "https://likms.assembly.go.kr/bill/billDetail.do?billId=" + BILL_ID;
    }
}
