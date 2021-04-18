package org.alphavantage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO implements DataObject {

    @JsonProperty(value = "symbol")
    private String symbol;
    @JsonProperty(value = "assetType")
    private String assetType;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "country")
    private String country;
    @JsonProperty(value = "sector")
    private String sector;
    @JsonProperty(value = "industry")
    private String industry;
    @JsonProperty(value = "address")
    private String address;
    @JsonProperty(value = "employees")
    private String employees;

}
