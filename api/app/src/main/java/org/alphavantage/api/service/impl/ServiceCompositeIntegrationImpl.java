package org.alphavantage.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.alphavantage.api.config.ApplicationProperties;
import org.alphavantage.api.dto.CompaniesDTO;
import org.alphavantage.api.dto.DailyPricesDTO;
import org.alphavantage.api.service.ServiceCompositeIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service data composite service which used as client for the Alpha Vantage API.
 */
@Slf4j
@Service
public class ServiceCompositeIntegrationImpl implements ServiceCompositeIntegration {

    private static final String FUNCTION_SEARCH = "&function=SYMBOL_SEARCH&keywords=";
    private static final String FUNCTION_PRICE = "&function=TIME_SERIES_DAILY&symbol=";

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CompaniesDTO getCompanies(String keywords) {
        val url = properties.getUrl() + FUNCTION_SEARCH + keywords;
        log.debug("Get companies data from URL: {}", url);

        try {
            val response = restTemplate.getForEntity(url, String.class);
            val body = mapper.readValue(response.getBody(), Map.class);
            val companies = CompaniesDTO.builder().bestMatches(new ArrayList<>()).build();
            for (Object o : (List<?>) body.get("bestMatches")) {
                val map = (Map<?, ?>) o;
                val company = CompaniesDTO.CompanyDTO.builder().build();
                for (val entry : map.entrySet()) {
                    val field = company.getClass().getDeclaredField(entry.getKey().toString().substring(3));
                    field.setAccessible(true);
                    field.set(company, entry.getValue());
                }
                companies.getBestMatches().add(company);
            }
            return companies;
        } catch (HttpClientErrorException | IOException | NoSuchFieldException | IllegalAccessException e) {
            log.debug("Error during receive data from external service {}", e.getMessage());
            throw new IllegalArgumentException("Error! Wrong address of the external service: " + properties.getUrl());
        }
    }

    @Override
    public DailyPricesDTO getDaily(String company) {
        val url = properties.getUrl() + FUNCTION_PRICE + company;
        log.debug("Get prices data from URL: {}", url);

        try {
            val response = restTemplate.getForEntity(url, String.class);
            val body = mapper.readValue(response.getBody(), Map.class);
            val prices = DailyPricesDTO.builder().prices(new ArrayList<>()).build();
            val s = (Map<String, String>) body.get("Time Series (Daily)");
            if (s != null) {
                for (Map.Entry<String, String> stringStringEntry : s.entrySet()) {
                    val actualValue = (Map.Entry<String, ?>) stringStringEntry;
                    val daily = DailyPricesDTO.Daily.builder().build();
                    daily.setDate(actualValue.getKey());
                    val map = (Map<?, ?>) actualValue.getValue();
                    for (val entry : map.entrySet()) {
                        val field = daily.getClass().getDeclaredField(entry.getKey().toString().substring(3));
                        field.setAccessible(true);
                        field.set(daily, entry.getValue());
                    }
                    prices.getPrices().add(daily);
                }
            }
            return prices;
        } catch (HttpClientErrorException | IOException | NoSuchFieldException | IllegalAccessException e) {
            log.debug("Error during receive data from external service {}", e.getMessage());
            throw new IllegalArgumentException("Error! Wrong address of the external service: " + properties.getUrl());
        }
    }

}
