package org.alphavantage.api.service;

import org.alphavantage.api.dto.CompaniesDTO;
import org.alphavantage.api.dto.CompanyDTO;
import org.alphavantage.api.dto.DailyPricesDTO;

public interface ServiceCompositeIntegration {

    DailyPricesDTO getDaily(String company);

    CompanyDTO getCompany(String symbol);

    CompaniesDTO getCompanies(String keywords);

}
