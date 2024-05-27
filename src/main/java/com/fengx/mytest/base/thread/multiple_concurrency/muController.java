package com.fengx.mytest.base.thread.multiple_concurrency;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * 简书点赞
 */
@Slf4j
@RequestMapping("/dianzan")
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class muController {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() throws InterruptedException {
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        new Thread(this::test1).start();
        Thread.sleep(5000);
    }

    private void test1() {
        String url = "http://localhost:8761/experimental-server/experimental/experiment/Student/create";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlxdWVfbmFtZSI6IjExMTExMTExIiwibG9naW5Vc2VyIjoiNGE0NWM3ZDY4ZTMxOTBkYzAxOGYwZjQ1NmRiOTJkYzQiLCJpc3MiOiJsbmxyIiwiYXVkIjoiMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYiLCJleHAiOjE3MTY5MzQxMDIsIm5iZiI6MTcxNjc5MDEwMn0.yuLb3CnivLFphn7eZjlx_hl4b2RnCvOn0U4VlO-UgHc");
        httpHeaders.set("Accept", "application/json");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONObject object = new JSONObject();
        object.put("arrangeId", "4028e4b18fb8a197018fb8a73890002b");
        object.put("courseProjectId", "4a45c7d678a510500178a6254ea800f7");
        object.put("laboratoryId", "4a45c7d678a510500178a61966e30093");
        object.put("studentIdList", new ArrayList<String>() {{
            add("4a45c7d68e3190dc018f0f518f9e2e25");
        }});
        String http = http(httpHeaders, url, HttpMethod.POST, object);
        JSONObject jsonObject = JSONObject.parseObject(http);
        System.out.println(jsonObject);
    }


    private String httpGet(HttpHeaders headers, String url) {
        return http(headers, url, HttpMethod.GET, null);
    }

    private <T> String http(HttpHeaders headers, String url, HttpMethod method, T body) {
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