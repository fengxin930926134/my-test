//package com.fengx.mytest.springboot.feign;
//
//import com.xzl.common.config.FeignConfig;
//import com.xzl.common.jpa.model.NgData;
//import com.xzl.common.jpa.model.NgPager;
//import com.xzl.common.response.ObjectResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * 超时配置
// * #feign
// * feign.client.config.default.ReadTimeout=60000
// * feign.client.config.default.ConnectTimeout=60000
// */
//@FeignClient(name = "laboratory-server", path = "laboratory/tc/inventory", configuration = FeignConfig.class)
//public interface FeignClientTest {
//
//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    ObjectResponse<NgData<?>> page(@RequestBody NgPager ngPager);
//}
