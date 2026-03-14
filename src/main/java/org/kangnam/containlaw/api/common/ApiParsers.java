package org.kangnam.containlaw.api.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.StringReader;
import java.util.function.Function;

public final class ApiParsers {
    private ApiParsers() {
    }

    public static <T> ApiResponseStrategy<T> text(Function<String, T> extractor) {
        return responseBody -> extractor.apply(responseBody);
    }

    public static <T> ApiResponseStrategy<T> html(Function<Document, T> extractor) {
        return responseBody -> extractor.apply(Jsoup.parse(responseBody));
    }

    public static <T> ApiResponseStrategy<T> json(ObjectMapper objectMapper, Function<JsonNode, T> extractor) {
        return responseBody -> {
            JsonNode root = objectMapper.readTree(responseBody);
            return extractor.apply(root);
        };
    }

    public static <R, T> ApiResponseStrategy<T> xml(Class<R> xmlType, Function<R, T> extractor) {
        return responseBody -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(xmlType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object parsed = unmarshaller.unmarshal(new StringReader(responseBody));
            return extractor.apply(xmlType.cast(parsed));
        };
    }
}
