package org.alphavantage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * A DTO representing a generic response object
 */
@Data
@Builder
@ApiModel
public class ResponseDTO<T extends DataObject> {

    @JsonProperty(value = "jsonapi")
    private OpenApiDTO.JsonAPI jsonAPI;
    @JsonProperty(value = "meta")
    private OpenApiDTO.Meta meta;
    @JsonProperty(value = "links")
    private OpenApiDTO.Links links;
    @JsonProperty(value = "data")
    private T data;

}
