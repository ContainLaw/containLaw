package org.kangnam.containlaw.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractApiService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;

    protected AbstractApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected String get(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    protected <T> T getAndParse(String url, ApiResponseStrategy<T> strategy) {
        try {
            String responseBody = get(url);
            return strategy.parse(responseBody == null ? "" : responseBody);
        } catch (Exception e) {
            logger.error("API call failed. url={}", url, e);
            throw new IllegalStateException("API call failed: " + url, e);
        }
    }
}
