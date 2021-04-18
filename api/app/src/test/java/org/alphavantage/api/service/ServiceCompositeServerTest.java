package org.alphavantage.api.service;

import org.alphavantage.api.config.ApplicationProperties;
import org.alphavantage.api.dto.CompaniesDTO;
import org.alphavantage.api.dto.DailyPricesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/")
public class ServiceCompositeServerTest {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private InfoService infoService;

    @GetMapping("/api/v1/json/query")
    public ResponseEntity getQuery(@RequestParam(value = "function") String function) {
        try {
            if ("SYMBOL_SEARCH".equalsIgnoreCase(function)) {
                CompaniesDTO dto = CompaniesDTO.builder()
                        .bestMatches(getCompanies())
                        .build();
                return new ResponseEntity<>(dto, HttpStatus.OK);
            } else if ("TIME_SERIES_DAILY".equals(function)) {
                DailyPricesDTO dto = DailyPricesDTO.builder()
                        .prices(getDaily())
                        .build();
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Cannot find function");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private List<CompaniesDTO.PriceDTO> getCompanies() {
        return IntStream.range(0, 1)
                .mapToObj(i -> {
                    CompaniesDTO.PriceDTO dto = CompaniesDTO.PriceDTO.builder()
                            .symbol("TESO")
                            .name("Tesco Corporation USA")
                            .type("Equity")
                            .region("United States")
                            .marketOpen("09:30")
                            .marketClose("16:00")
                            .timezone("UTC-04")
                            .currency("USD")
                            .matchScore("0.8889")
                            .build();
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private List<DailyPricesDTO.Daily> getDaily() {
        return IntStream.range(0, 1)
                .mapToObj(i -> {
                    DailyPricesDTO.Daily dto = DailyPricesDTO.Daily.builder()
                            .date("2021-04-13")
                            .open("133.0000")
                            .high("133.6200")
                            .low("130.3800")
                            .close("131.1800")
                            .volume("8033530")
                            .build();
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
