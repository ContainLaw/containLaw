package org.kangnam.containlaw.Dto;

import lombok.Getter;

@Getter
public class ResponseData {
    private final String summary;
    private final String category;
    private final String advantages;
    private final String disadvantages;

    public ResponseData(String summary, String category, String advantages, String disadvantages) {
        this.summary = summary;
        this.category = category;
        this.advantages = advantages;
        this.disadvantages = disadvantages;
    }
}
