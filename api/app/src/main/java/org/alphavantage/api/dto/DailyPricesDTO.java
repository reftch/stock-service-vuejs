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
public class DailyPricesDTO implements DataObject {

    @JsonProperty(value = "prices")
    private List<Daily> prices;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Daily {
        @JsonProperty(value = "date")
        private String date;
        @JsonProperty(value = "open")
        private String open;
        @JsonProperty(value = "high")
        private String high;
        @JsonProperty(value = "low")
        private String low;
        @JsonProperty(value = "close")
        private String close;
        @JsonProperty(value = "volume")
        private String volume;
    }

}
