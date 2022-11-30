package com.fengx.mytest.external.minio;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FileClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "minio")
    public Properties getProperties(){
        return new Properties();
    }

    @Bean
    public FileClient fileClient() {
        Properties properties = getProperties();
        log.info("file client init in "
                .concat(properties.getApi()).concat(" ")
                .concat(properties.getAccesskey()).concat(" ")
                .concat(properties.getSecretkey()));
        return new FileClient(properties.getApi(), properties.getAccesskey(), properties.getSecretkey());
    }

    @Data
    private static class Properties {
        private String api;
        private String accesskey;
        private String secretkey;
    }
}
