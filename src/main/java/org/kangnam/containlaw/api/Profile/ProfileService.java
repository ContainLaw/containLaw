package org.kangnam.containlaw.api.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProfileService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
//    @Scheduled(fixedRate = 100000) // test
    public String getProfileImgPath(String name) {
        String url = createProfileImgPath(name);
//        String url = createProfileImgPath("강기윤");
        String jsonData = restTemplate.getForObject(url, String.class);
        try {
            JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode deptImgUrlNode = rootNode.path("data").get(0).path("deptImgUrl");
            return deptImgUrlNode.asText();
        } catch (Exception e) {
            System.out.println("이미지를 불러오는데 실패했습니다.");
        }
        return "";
    }
    public String createProfileImgPath(String name) {
        return "https://open.assembly.go.kr/portal/assm/search/searchAssmMemberSch.do?statusCd=060001?unitCd=100021&gubunId=MA&schHgNm="
                + name;
    }
}
