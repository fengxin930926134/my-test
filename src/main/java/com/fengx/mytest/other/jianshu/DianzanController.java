package com.fengx.mytest.other.jianshu;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简书点赞
 */
@Slf4j
@RequestMapping("/dianzan")
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DianzanController {

    private static final Integer sleepTime = 2000;
    private static final Integer max = 56;
    private static Boolean exit = false;

    /**
     * 判断是否已点赞的字符
     */
    private static final String vDianZan = "_3oieia";

    private static String Cookie = "_ga=GA1.2.538624253.1644386005; UM_distinctid=17edd0b33c068d-057a92641ba9bb-33544874-1fa400-17edd0b33c1a50; __yadk_uid=tvbZYGY5BLMbSxzU61kuvARpFzCllWhz; _gid=GA1.2.1725469842.1652079791; CNZZDATA1279807957=1690344059-1644375653-https%253A%252F%252Fwww.baidu.com%252F%7C1652168821; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2219619388%22%2C%22first_id%22%3A%2217edd0b30c4efa-004957b4186591-33544874-2073600-17edd0b30c5358%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_utm_source%22%3A%22index-aside-click%22%2C%22%24latest_utm_medium%22%3A%22desktop%22%2C%22%24latest_utm_campaign%22%3A%22maleskine%22%2C%22%24latest_utm_content%22%3A%22note%22%2C%22%24latest_referrer_host%22%3A%22%22%7D%2C%22%24device_id%22%3A%2217edd0b30c4efa-004957b4186591-33544874-2073600-17edd0b30c5358%22%7D; Hm_lvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1652235849,1652237445,1652245340,1652317219; Hm_lpvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1652317219; remember_user_token=W1sxOTYxOTM4OF0sIiQyYSQxMSRMODdtN1ZkZWp2TER0YjF1b0lYVC9PIiwiMTY1MjMxNzIxNi45NDcyMjU4Il0%3D--33d6d49996971ca8a3cc763c997a940905e020b7; locale=zh-CN; _m7e_session_core=a586c8ca0441d8dac312ca54c3c3a9e2";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/setCookie")
    public String setCookie(@RequestBody String cookie) {
        Cookie = cookie;
        return "成功";
    }

    @Test
    @Scheduled(cron = "0 0 */6 * * ?")
    @GetMapping("/index")
    public void test() {
        choujiang();
        exit = false;
        log.info("开始点赞..." + LocalDateTime.now());
        List<String> ids = Lists.newArrayList();
        try {
            String homePage = getHomePage();
            String csrfToken = getCsrfToken(homePage);
            ids.addAll(getIds(homePage));

            if (!exit) {
                Thread.sleep(sleepTime);
                String twoPage = getByPage(ids, 2, csrfToken);
                ids.addAll(getIds(twoPage));
            }

            if (!exit) {
                Thread.sleep(sleepTime);
                String threePage = getByPage(ids, 3, csrfToken);
                ids.addAll(getIds(threePage));
            }

            for (int i = 4; !exit; i++) {
                Thread.sleep(sleepTime);
                String nextPage = getNextPage(ids, i, csrfToken);
                ids.addAll(getIds(nextPage));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("执行错误");
        }
        log.info("结束点赞..." + LocalDateTime.now());

    }

    private String getCsrfToken(String html) {
        String token = "";
        String pattern3 = "(<meta name=\"csrf-token\" content=\"\\S*\")";
        Matcher match3 = Pattern.compile(pattern3).matcher(html);
        if (match3.find()) {
            String replace = match3.group()
                    .replace("<meta name=\"csrf-token\" content=\"", "");
            return replace.substring(0, replace.length() - 1);
        }
        return token;
    }

    private List<String> getIds(String html) throws InterruptedException {
        List<String> ids = Lists.newArrayList();

        String pattern = "(data-note-id=\"\\S*\")";
        Matcher match = Pattern.compile(pattern).matcher(html);
        while (match.find()) {
            ids.add(match.group().replace("data-note-id=\"", "")
                    .replace("\"", ""));
        }

        // 页面地址
        List<String> urls = Lists.newArrayList();
        String pattern4 = "(href=\"/p/\\S*#comments)";
        Matcher match4 = Pattern.compile(pattern4).matcher(html);
        while (match4.find()) {
            urls.add("https://www.jianshu.com" + match4.group()
                    .replace("href=\"", "").replace("#comments", ""));
        }

        // 获取能力值
        List<Double> values = Lists.newArrayList();
        String pattern2 = "(<i class=\"iconfont ic-paid1\"></i> \\S*)";
        Matcher match2 = Pattern.compile(pattern2).matcher(html);
        while (match2.find()) {
            values.add(Double.parseDouble(match2.group().replace("<i class=\"iconfont ic-paid1\"></i> ", "")));
        }

        for (int i = 0; i < values.size() && !exit; i++) {
            Double aDouble = values.get(i);
            if (aDouble > 90) {
                String fangwen = fangwen(urls.get(i));
                if (isDianzan(fangwen)) {
                    log.info("已点赞，跳过");
                } else {
                    Thread.sleep(sleepTime);
                    if (aDouble > 180) {
                        dianzan(ids.get(i), 2);
                    } else {
                        dianzan(ids.get(i), 1);
                    }
                }
                Thread.sleep(sleepTime);
            }
        }

        return ids;
    }

    /**
     * 是否已点赞
     */
    private boolean isDianzan(String html) {
        String pattern = "(" + vDianZan + ")";
        Matcher match = Pattern.compile(pattern).matcher(html);
        return match.find();
    }

    private String getHomePage() {
        String url = "https://www.jianshu.com/";
        return http(new HttpHeaders(), url, HttpMethod.GET);
    }

    private String getByPage(List<String> ids, int page, String XCSRFToken) {
        String collect = String.join("&seen_snote_ids%5B%5D=", ids);
        String url = "https://www.jianshu.com/?seen_snote_ids%5B%5D=" + collect + "&page=" + page;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-CSRF-Token", XCSRFToken);
        httpHeaders.set("Accept", "text/html, */*; q=0.01");
        httpHeaders.set("X-INFINITESCROLL", "true");
        httpHeaders.set("X-Requested-With", "XMLHttpRequest");
        return http(httpHeaders, url, HttpMethod.GET);
    }

    private String getNextPage(List<String> ids, int page, String XCSRFToken) {
        String url = "https://www.jianshu.com/trending_notes";
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        ids.forEach(id -> params.add("seen_snote_ids[]", id));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-CSRF-Token", XCSRFToken);
        httpHeaders.set("Accept", "text/html, */*; q=0.01");
        httpHeaders.set("X-PJAX", "true");
        httpHeaders.set("X-Requested-With", "XMLHttpRequest");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return http(httpHeaders, url, HttpMethod.POST, params);
    }

    private void dianzan(String id, int number) {
        String url = "https://www.jianshu.com/shakespeare/notes/" + id + "/like";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject object = new JSONObject();
        object.put("energy_point", number);
        object.put("note_id", Integer.parseInt(id));
        String http = http(httpHeaders, url, HttpMethod.POST, object);
        JSONObject jsonObject = JSONObject.parseObject(http);
        int remainingEnergyPoint = jsonObject.getIntValue("remaining_energy_point");
        log.info("number:" + number + ",点赞后能量：" + remainingEnergyPoint);
        if (remainingEnergyPoint <= max) {
            exit = true;
        }
    }

    /**
     * 抽奖
     */
    private void choujiang() {
        log.info("开始抽奖...");
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", "application/json");
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject object = new JSONObject();
            object.put("fuc", 1);
            String http = http(httpHeaders, "https://www.jianshu.com/asimov/user_ad_reward/v2_draw", HttpMethod.POST, object);
            JSONObject object1 = JSONObject.parseObject(http);
            if (object1.containsKey("error")) {
                log.info("抽奖时间未到");
            } else {
                int left_count = object1.getIntValue("left_count");
                if (left_count > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    choujiang();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String fangwen(String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        httpHeaders.add("If-None-Match", "212b4-9A6TFs/FDMVVMQClhmFWczKC5no");
        httpHeaders.add("Upgrade-Insecure-Requests", "1");
        // 返回的是网页
        return http(httpHeaders, url, HttpMethod.GET);
    }

    private String http(HttpHeaders headers, String url, HttpMethod method) {
        return http(headers, url, method, null);
    }

    private <T> String http(HttpHeaders headers, String url, HttpMethod method, T body) {
        headers.set("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Mobile Safari/537.36");
        headers.set("Cookie", Cookie);
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
