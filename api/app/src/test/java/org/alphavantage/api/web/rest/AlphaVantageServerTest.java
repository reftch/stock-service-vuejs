package org.alphavantage.api.web.rest;

import org.alphavantage.api.config.ApplicationProperties;
import org.alphavantage.api.dto.InfoDTO;
import org.alphavantage.api.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AlphaVantageServerTest {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private InfoService infoService;

    @GetMapping("/api/v1/json/info")
    public ResponseEntity getInfo() {
        try {
            return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
