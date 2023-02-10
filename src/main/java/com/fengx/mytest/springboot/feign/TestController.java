//package com.fengx.mytest.springboot.feign;
//
//import com.xzl.common.jpa.model.NgPager;
//import com.xzl.common.response.Response;
//import com.xzl.service.FeignClientTest;
//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/test")
//@Api(value = "testaaa", tags="testaaa")
//@Slf4j
//@RequiredArgsConstructor
//public class TestController {
//
//    private final FeignClientTest feignClientTest;
//
//    @GetMapping("/test1")
//    public Response test() {
//        Response page = feignClientTest.page(new NgPager());
//        System.out.println(page);
//        return page;
//    }
//}
