package org.alphavantage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OpenApiDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonAPI {
        @JsonProperty(value = "version")
        private String version = "1.0";
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        @JsonProperty(value = "build")
        private String buildDate;
        @JsonProperty(value = "api")
        private String api;
        @JsonProperty(value = "version")
        private String version;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Links {
        @JsonProperty(value = "self")
        private String self;
    }
}
