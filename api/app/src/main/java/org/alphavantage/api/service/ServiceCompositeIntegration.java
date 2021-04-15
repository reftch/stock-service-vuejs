package org.alphavantage.api.service;

import org.alphavantage.api.dto.CompaniesDTO;
import org.alphavantage.api.dto.DailyPricesDTO;

public interface ServiceCompositeIntegration {

    CompaniesDTO getCompanies(String keywords);

    DailyPricesDTO getDaily(String company);

}
