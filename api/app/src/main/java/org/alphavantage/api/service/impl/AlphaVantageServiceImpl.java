package org.alphavantage.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.alphavantage.api.dto.DailyPricesDTO;
import org.alphavantage.api.dto.ResponseDTO;
import org.alphavantage.api.service.AlphaVantageService;
import org.alphavantage.api.service.ServiceCompositeIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation.
 */
@Slf4j
@Service
public class AlphaVantageServiceImpl extends CommonService implements AlphaVantageService {

    @Autowired
    ServiceCompositeIntegration compositeService;

    @Override
    public ResponseDTO getCompanies(String keywords) {
        val response = getResponseDTO("/companies");
        val companies = compositeService.getCompanies(keywords);
        if (companies != null) {
            response.setData(companies);
        }
        return response;
    }

    @Override
    public ResponseDTO<DailyPricesDTO> getDaily(String company) {
        val response= getResponseDTO("/daily");
        val daily = compositeService.getDaily(company);
        if (daily != null) {
            response.setData(daily);
        }
        return response;
    }
}
