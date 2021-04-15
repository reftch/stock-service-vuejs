package org.alphavantage.api.service.impl;

import org.alphavantage.api.dto.DataObject;
import org.alphavantage.api.dto.OpenApiDTO;
import org.alphavantage.api.dto.ResponseDTO;

import java.time.LocalDateTime;

public class CommonService {

    protected ResponseDTO getResponseDTO(String self) {
        return ResponseDTO.builder()
                .jsonAPI(OpenApiDTO.JsonAPI.builder()
                        .version("1.1")
                        .build())
                .meta(OpenApiDTO.Meta.builder()
                        .api("v1")
                        .buildDate(LocalDateTime.now().toString())
                        .version("1.0.0")
                        .build())
                .links(OpenApiDTO.Links.builder()
                        .self(self)
                        .build())
                .build();
    }
}
