package org.kangnam.containlaw.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kangnam.containlaw.Dto.OpenAiRequest;
import org.kangnam.containlaw.Dto.ResponseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ChatService {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseData getAllInformation(String text) throws IOException, InterruptedException {
        String truncatedText = truncateText(text, 4000);
        String prompt = String.join("\n",
                "다음 입법안 내용을 이해하기 쉽게 요약해줘(varchar 1000 미만): " + truncatedText,
                "다음 입법안 내용이 괄호 안 16개 카테고리(경제, 보건, 교육, 환경, 노동, 사회 복지, 법 집행 및 사법, 인권 및 평등, 국제 관계, 안보 및 국방, 부동산 및 토지 관리, 가족 및 개인, 정보 및 통신, 교통 및 물류, 문화 및 예술, 과학 및 기술.) 중 해당하는 카테고리 하나 알려줘. 주의: 오직 이 16개 카테고리 중 하나여야 함.: " + truncatedText,
                "입법안이 집행되었을 때의 장점(varchar 255 미만), " +
                "입법안이 집행되었을 때의 단점(varchar 255 미만), " +
                "출력 결과를 'FLAG'라는 문자를 사용해서 구분하고 싶어. '요약내용 FLAG 카테고리 FLAG 장점 FLAG 단점' 으로 출력해줘"
        );

        String body = objectMapper.writeValueAsString(new OpenAiRequest(prompt));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return parseResponseForAllInformation(response.body());
    }

    private String truncateText(String text, int maxLength) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= maxLength) {
            return text;
        }
        return new String(bytes, 0, maxLength, StandardCharsets.UTF_8);
    }

    private ResponseData parseResponseForAllInformation(String responseBody) throws IOException {
        JsonNode rootNode = objectMapper.readTree(responseBody);
        JsonNode choicesNode = rootNode.path("choices");
        if (choicesNode.isArray() && choicesNode.size() > 0) {
            JsonNode messageNode = choicesNode.get(0).path("message").path("content");
            if (!messageNode.isMissingNode()) {
                String[] responses = messageNode.asText().split("FLAG");
                String summary = responses.length > 0 ? responses[0].replace("요약내용:", "").trim() : "";
                String category = responses.length > 1 ? responses[1].replace("카테고리:", "").trim() : "";
                String advantages = responses.length > 2 ? responses[2].replace("장점:", "").trim() : "";
                String disadvantages = responses.length > 3 ? responses[3].replace("단점:", "").trim() : "";
                System.out.println("카테고리:"+category);
                return new ResponseData(summary, category, advantages, disadvantages);
            }
        }
        throw new IOException("Unexpected response format: " + responseBody);
    }
}