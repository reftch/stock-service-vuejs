package org.alphavantage.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationConfigTest {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
        assertThat(mapper).isNotNull();
        assertThat(properties.getAlphaVantageApi()).isEqualTo("http://localhost:8888/api/v1/json");
    }
}
