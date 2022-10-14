package com.fengx.mytest.springboot.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HttpUtils {

    @Autowired
    private RestTemplate restTemplate;

    public String httpGet(HttpHeaders headers, String url) {
        return http(headers, url, HttpMethod.GET, null);
    }

    public  <T> String http(HttpHeaders headers, String url, HttpMethod method, T body) {
        HttpEntity<T> entity;
        if (body == null) {
            entity = new HttpEntity<>(headers);
        } else {
            entity = new HttpEntity<>(body, headers);
        }
        log.info("req " + method.name() + " url:" + url);
        ResponseEntity<String> results = restTemplate.exchange(url, method, entity, String.class);
        return results.getBody();
    }
}
