package org.alphavantage.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.alphavantage.api.service.AlphaVantageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AlphaVantageResource {

    private final AlphaVantageService alphaVantageService;

    public AlphaVantageResource(AlphaVantageService alphaVantageService) {
        this.alphaVantageService = alphaVantageService;
    }

    @GetMapping("/companies")
    public ResponseEntity getCompanies(@RequestParam(value="keywords", required = false) String keywords) {
        log.info("REST request to get companies by keywords {}", keywords);
        try {
            return new ResponseEntity<>(alphaVantageService.getCompanies(keywords), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/daily")
    public ResponseEntity getDaily(@RequestParam(value="company", required = false) String company) {
        log.info("REST request to get companies by company {}", company);
        try {
            return new ResponseEntity<>(alphaVantageService.getDaily(company), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
