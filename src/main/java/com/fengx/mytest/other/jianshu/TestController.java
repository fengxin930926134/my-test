package com.fengx.mytest.other.jianshu;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() throws InterruptedException {
        List<String> ids = Lists.newArrayList();

        ids.addAll(getIds(getHomePage()));

        Thread.sleep(1000);
        String twoPage = getByPage(ids, 2);

        ids.addAll(getIds(twoPage));

        Thread.sleep(1000);
        String threePage = getByPage(ids, 3);
        log.info(threePage);
    }

    private List<String> getIds(String html) {
        List<String> ids = Lists.newArrayList();

        String pattern = "(data-note-id=\"\\S*\")";
        Matcher match = Pattern.compile(pattern).matcher(html);
        while (match.find()) {
            ids.add(match.group().replace("data-note-id=\"", "")
                    .replace("\"", ""));
        }
        return ids;
    }

    private String getHomePage() {
        String url = "https://www.jianshu.com/";
        return get(url);
    }

    private String getByPage(List<String> ids, int page) {
        String collect = String.join("&seen_snote_ids%5B%5D=", ids);
        String url = "https://www.jianshu.com/?seen_snote_ids%5B%5D=" + collect + "&page=" + page;
        return get(url);
    }

    private String getNextPage(List<String> ids, int page) {
        String url = "https://www.jianshu.com/trending_notes";
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3883.400 QQBrowser/10.8.4582.400");
        headers.set("Cookie", "_ga=GA1.2.538624253.1644386005; UM_distinctid=17edd0b33c068d-057a92641ba9bb-33544874-1fa400-17edd0b33c1a50; __yadk_uid=tvbZYGY5BLMbSxzU61kuvARpFzCllWhz; web_login_version=MTY0ODA4NDUxMA%3D%3D--9592b6027812044ecce7a71d7373ae3a38f2f63c; _gid=GA1.2.441074158.1649216108; remember_user_token=W1sxOTYxOTM4OF0sIiQyYSQxMSRMODdtN1ZkZWp2TER0YjF1b0lYVC9PIiwiMTY0OTM4MzA2OS41MDg4Njk0Il0%3D--630ef01daa25b7df8465c05f00f84d002ebcfcdf; read_mode=day; default_font=font2; locale=zh-CN; _m7e_session_core=0543381d85a067212d8bdc7ec7b72daf; Hm_lvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1648789461,1649238087,1649322037,1649383072; CNZZDATA1279807957=1690344059-1644375653-https%253A%252F%252Fwww.baidu.com%252F%7C1649398055; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2219619388%22%2C%22first_id%22%3A%2217edd0b30c4efa-004957b4186591-33544874-2073600-17edd0b30c5358%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_utm_source%22%3A%22recommendation%22%2C%22%24latest_utm_medium%22%3A%22seo_notes%22%2C%22%24latest_utm_campaign%22%3A%22maleskine%22%2C%22%24latest_utm_content%22%3A%22note%22%7D%2C%22%24device_id%22%3A%2217edd0b30c4efa-004957b4186591-33544874-2073600-17edd0b30c5358%22%7D; Hm_lpvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1649404886");
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        ids.forEach(id -> params.add("seen_snote_ids[]", id));
        return restTemplate.postForObject(url, params, String.class);
    }

    private String get(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3883.400 QQBrowser/10.8.4582.400");
        headers.set("Cookie", "_ga=GA1.2.538624253.1644386005; UM_distinctid=17edd0b33c068d-057a92641ba9bb-33544874-1fa400-17edd0b33c1a50; __yadk_uid=tvbZYGY5BLMbSxzU61kuvARpFzCllWhz; web_login_version=MTY0ODA4NDUxMA%3D%3D--9592b6027812044ecce7a71d7373ae3a38f2f63c; _gid=GA1.2.441074158.1649216108; remember_user_token=W1sxOTYxOTM4OF0sIiQyYSQxMSRMODdtN1ZkZWp2TER0YjF1b0lYVC9PIiwiMTY0OTM4MzA2OS41MDg4Njk0Il0%3D--630ef01daa25b7df8465c05f00f84d002ebcfcdf; read_mode=day; default_font=font2; locale=zh-CN; _m7e_session_core=0543381d85a067212d8bdc7ec7b72daf; Hm_lvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1648789461,1649238087,1649322037,1649383072; CNZZDATA1279807957=1690344059-1644375653-https%253A%252F%252Fwww.baidu.com%252F%7C1649398055; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2219619388%22%2C%22first_id%22%3A%2217edd0b30c4efa-004957b4186591-33544874-2073600-17edd0b30c5358%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_utm_source%22%3A%22recommendation%22%2C%22%24latest_utm_medium%22%3A%22seo_notes%22%2C%22%24latest_utm_campaign%22%3A%22maleskine%22%2C%22%24latest_utm_content%22%3A%22note%22%7D%2C%22%24device_id%22%3A%2217edd0b30c4efa-004957b4186591-33544874-2073600-17edd0b30c5358%22%7D; Hm_lpvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1649404886");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return results.getBody();
    }

//    @Override
//    public String uploadWeiXiMaterial(String accessToken, String fileType, File file) {
//        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?" +
//                "access_token=" + accessToken +
//                "&type=" + fileType;
//        FileSystemResource resource = new FileSystemResource(file);
//        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//        params.add("media", resource);
//        String json = restTemplate.postForObject(url, params, String.class);
//        JSONObject object = JSONUtils.string2Json(json);
//        verification(object);
//        return object.getString("media_id");
//    }
//
//    @Override
//    public String addWeiXiEssay(String accessToken, JSONObject body) {
//        String url = "https://api.weixin.qq.com/cgi-bin/draft/add?" +
//                "access_token=" + accessToken;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> formEntity = new HttpEntity<>(body.toString(), headers);
//        String json = restTemplate.postForObject(url, formEntity, String.class);
//        JSONObject object = JSONUtils.string2Json(json);
//        verification(object);
//        return object.getString("media_id");
//    }

}
