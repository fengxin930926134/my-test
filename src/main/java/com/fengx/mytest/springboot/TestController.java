package com.fengx.mytest.springboot;

import com.fengx.mytest.springboot.response.FailedResponse;
import com.fengx.mytest.springboot.response.ObjectResponse;
import com.fengx.mytest.springboot.response.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/index")
    public Response index(HttpServletRequest request) {
        log.info("调用了/test/index");
        String aaA = request.getHeader("AaA");
        log.info("aaA:" + aaA);
        User user = new User();
        user.id = "111";
        user.name = "哈哈哈";
        return new ObjectResponse<>(user);
    }

    @Data
    static class User {

        String name;
        String id;
    }
}
