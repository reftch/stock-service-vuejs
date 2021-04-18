package org.alphavantage.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.alphavantage.api.service.AlphaVantageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AlphaVantageResource {

    private final AlphaVantageService alphaVantageService;

    public AlphaVantageResource(AlphaVantageService alphaVantageService) {
        this.alphaVantageService = alphaVantageService;
    }

    /**
     * Returns list of companies
     * <p>
     * GET /api/v1/search?keywords=aa.
     *
     * @param keywords
     * @return the ResponseEntity with status 200 (OK) and with body or with status 404 (Not Found)
     * <p>
     * The method returns one of the following status codes:
     * <p>
     * 200 - Successful completion
     * 400 - The parameters are not valid or missing
     */
    @GetMapping("/search")
    public ResponseEntity getCompanies(@RequestParam(value="keywords", required = false) String keywords) {
        log.info("REST request to get companies by keywords {}", keywords);
        try {
            return new ResponseEntity<>(alphaVantageService.getCompanies(keywords), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Returns basic information about the company
     * <p>
     * GET /api/v1/company?symbol=AAPL
     *
     * @param symbol comapny symbol
     * @return the ResponseEntity with status 200 (OK) and with body or with status 404 (Not Found)
     * <p>
     * The method returns one of the following status codes:
     * <p>
     * 200 - Successful completion
     * 400 - The parameters are not valid or missing
     */
    @GetMapping("/company")
    public ResponseEntity getCompany(@RequestParam(value="symbol", required = true) String symbol) {
        log.info("REST request to get company info by symbol {}", symbol);
        try {
            return new ResponseEntity<>(alphaVantageService.getCompany(symbol), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Returns daily stock quotes
     * <p>
     * GET /api/v1/prices?company=AAPL
     *
     * @param symbol company symbol
     * @return the ResponseEntity with status 200 (OK) and with body or with status 404 (Not Found)
     * <p>
     * The method returns one of the following status codes:
     * <p>
     * 200 - Successful completion
     * 400 - The parameters are not valid or missing
     */
    @GetMapping("/prices")
    public ResponseEntity getDaily(@RequestParam(value="symbol", required = false) String symbol) {
        log.info("REST request to get companies by company {}", symbol);
        try {
            return new ResponseEntity<>(alphaVantageService.getDaily(symbol), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
