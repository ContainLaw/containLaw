package org.kangnam.containlaw.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatgptService chatgptService;

    // 입법안 내용을 카테고리로 분류하는 메서드
    public String determineCategory(String lsmLegContent) {
        String prompt = "다음 입법안 내용을 어린 아이들도 이해하기 쉽게 요약해 주세요. 요약 내용 뒤에는 해당 입법안이 어떤 카테고리에 속하는지 적어주세요. " +
                "카테고리는 다음 16개 중에서 선택해주세요: 경제, 보건, 교육, 환경, 노동, 사회 복지, 법 집행 및 사법, 인권 및 평등, 국제 관계, 안보 및 국방, " +
                "부동산 및 토지 관리, 가족 및 개인, 정보 및 통신, 교통 및 물류, 문화 및 예술, 과학 및 기술. " +
                "또한 이 법안의 장점과 단점도 설명해 주세요. " +
                "입법안 내용: " + lsmLegContent + " 요약 내용: 카테고리: 장점: 단점:";

        String response = chatgptService.sendMessage(prompt);

        // Assuming the response is in a specific format that can be split
        String[] parts = response.split("요약 내용:|카테고리:|장점:|단점:");
        String summary = parts.length > 1 ? parts[1].trim() : "";
        String category = parts.length > 2 ? parts[2].trim() : "";
        String advantages = parts.length > 3 ? parts[3].trim() : "";
        String disadvantages = parts.length > 4 ? parts[4].trim() : "";

        return String.format("[요약 내용]: %s\n[카테고리]: %s\n[장점]: %s\n[단점]: %s", summary, category, advantages, disadvantages);
    }
}