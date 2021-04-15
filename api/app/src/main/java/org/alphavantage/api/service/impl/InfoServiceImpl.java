package org.alphavantage.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.alphavantage.api.config.ApplicationProperties;
import org.alphavantage.api.dto.InfoDTO;
import org.alphavantage.api.dto.ResponseDTO;
import org.alphavantage.api.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation.
 */
@Slf4j
@Service
public class InfoServiceImpl extends CommonService implements InfoService {

    @Autowired
    ApplicationProperties properties;

    @Override
    public ResponseDTO<InfoDTO> getInfo() {
        val response = getResponseDTO("/info");
        response.setData(InfoDTO.builder()
                        .alphaVantageApi(properties.getAlphaVantageApi())
                        .build());
        return response;
    }
}
