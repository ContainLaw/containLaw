package org.kangnam.containlaw.api.common;

@FunctionalInterface
public interface ApiResponseStrategy<T> {
    T parse(String responseBody) throws Exception;
}
