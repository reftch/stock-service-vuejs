package org.alphavantage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesDTO implements DataObject {

    @JsonProperty(value = "bestMatches")
    private List<CompanyDTO> bestMatches;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyDTO  {

        @JsonProperty(value = "symbol")
        private String symbol;
        @JsonProperty(value = "name")
        private String name;
        @JsonProperty(value = "type")
        private String type;
        @JsonProperty(value = "region")
        private String region;
        @JsonProperty(value = "marketOpen")
        private String marketOpen;
        @JsonProperty(value = "marketClose")
        private String marketClose;
        @JsonProperty(value = "timezone")
        private String timezone;
        @JsonProperty(value = "currency")
        private String currency;
        @JsonProperty(value = "matchScore")
        private String matchScore;

    }

}
