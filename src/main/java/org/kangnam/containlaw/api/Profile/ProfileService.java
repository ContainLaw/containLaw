package org.kangnam.containlaw.api.Profile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kangnam.containlaw.api.common.AbstractApiService;
import org.kangnam.containlaw.api.common.ApiParsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProfileService extends AbstractApiService {

    private final ObjectMapper objectMapper;

    @Autowired
    public ProfileService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate);
        this.objectMapper = objectMapper;
    }

    public String getProfileImgPath(String name) {
        String url = createProfileImgPath(name);
        return getAndParse(url, ApiParsers.json(objectMapper, this::parseProfileImageUrl));
    }

    private String parseProfileImageUrl(JsonNode rootNode) {
        JsonNode deptImgUrlNode = rootNode.path("data").get(0).path("deptImgUrl");
        return deptImgUrlNode.asText("");
    }

    public String createProfileImgPath(String name) {
        return "https://open.assembly.go.kr/portal/assm/search/searchAssmMemberSch.do?statusCd=060001?unitCd=100021&gubunId=MA&schHgNm="
                + name;
    }
}
