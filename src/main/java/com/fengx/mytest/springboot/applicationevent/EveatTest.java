package com.fengx.mytest.springboot.applicationevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("/eveatTest")
@RestController
public class EveatTest {

    @Autowired
    private ApplicationContext context;

    @RequestMapping("/index")
    public String test() {
        UserEvent userEvent = new UserEvent("object",28,"张三");
        context.publishEvent(userEvent);
        return "sss";
    }
}