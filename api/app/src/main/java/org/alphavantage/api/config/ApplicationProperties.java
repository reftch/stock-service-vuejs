package org.alphavantage.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {

    private String alphaVantageApi;
    private String version;
    private String key;

    public String getUrl() {
        return this.getAlphaVantageApi() + "query?apikey=" + this.key;
    }

    private Swagger swagger = new Swagger();

    @Data
    public class Swagger {
        private String basePackage;
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
    }
}
