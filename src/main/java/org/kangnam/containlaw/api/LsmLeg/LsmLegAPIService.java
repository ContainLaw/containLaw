package org.kangnam.containlaw.api.LsmLeg;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegReq;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
import org.kangnam.containlaw.api.common.AbstractApiService;
import org.kangnam.containlaw.api.common.ApiParsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class LsmLegAPIService extends AbstractApiService {
    @Value("${external.api.lsm-lm-key}")
    private String key;

    @Autowired
    public LsmLegAPIService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public List<LsmLegRes.LsmLeg> getRows(LsmLegReq lsmLegReq) {
        String url = makeLsmLegHeaderReqUrl(lsmLegReq);
        return getAndParse(url, ApiParsers.xml(LsmLegRes.class, this::extractRows));
    }

    public List<Proposer> getProposerList(LsmLegRes.LsmLeg row) {
        String url = makeLsmLegProposerReqUrl(row);
        return getAndParse(url, ApiParsers.html(doc -> parseProposerList(doc, row)));
    }

    public String getLsmLegContent(String billId) {
        String url = createLsmLegContentUrl(billId);
        return getAndParse(url, ApiParsers.html(this::parseBillContent));
    }

    private List<LsmLegRes.LsmLeg> extractRows(LsmLegRes lsmLegRes) {
        if (lsmLegRes == null) {
            return Collections.emptyList();
        }
        LsmLegRes.ResResult result = lsmLegRes.getResult();
        if (result != null && !"INFO-000".equals(result.getCode())) {
            logger.warn("LSM API returned non-success code={}, message={}", result.getCode(), result.getMessage());
            return Collections.emptyList();
        }
        return Objects.requireNonNullElse(lsmLegRes.getRows(), Collections.emptyList());
    }

    private List<Proposer> parseProposerList(Document doc, LsmLegRes.LsmLeg row) {
        List<Proposer> proposerList = new ArrayList<>();
        Elements aTextList = doc.select("#periodDiv > div.layerInScroll.coaTxtScroll > div.links.textType02.mt20 > a");

        if (aTextList.isEmpty()) {
            proposerList.add(new Proposer(row.getBillNo(), row.getProposer(), "", ""));
            return proposerList;
        }

        for (Element aText : aTextList) {
            String[] info = aText.text().split("\\(|\\/|\\)");
            String name = info.length > 0 ? info[0] : "";
            String group = info.length > 1 ? info[1] : "";
            String chineseName = info.length > 2 ? info[2] : "";
            proposerList.add(new Proposer(row.getBillNo(), name, group, chineseName));
        }
        return proposerList;
    }

    private String parseBillContent(Document doc) {
        Elements content = doc.select("#summaryContentDiv");
        return content.text();
    }

    private String makeLsmLegHeaderReqUrl(LsmLegReq lsmLegReq) {
        return "https://open.assembly.go.kr/portal/openapi/TVBPMBILL11?Key=" + key + lsmLegReq;
    }

    private String makeLsmLegProposerReqUrl(LsmLegRes.LsmLeg row) {
        return "https://likms.assembly.go.kr/bill/coactorListPopup.do?billId=" + row.getBillId();
    }

    private String createLsmLegContentUrl(String billId) {
        return "https://likms.assembly.go.kr/bill/billDetail.do?billId=" + billId;
    }
}
