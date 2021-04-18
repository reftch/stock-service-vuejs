package org.alphavantage.api.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.alphavantage.api.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class InfoResource {

    @Autowired
    private InfoService infoService;

    @GetMapping("/info")
    public ResponseEntity getInfo() {
        log.info("REST request to get information about this service");
        try {
            return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
