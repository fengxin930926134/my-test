package com.fengx.mytest.springboot.longpolling;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RequestMapping("/async")
@RestController
public class AsyncRequestDemo {

    @Autowired
    private AsyncRequestService asyncRequestService;

    @GetMapping("/value")
    public String getValue() {
        postValue("aaaaaaaaaaaaaa");
        String msg =  null;
        Future<String> result = null;
        try{
            result = asyncRequestService.getValue();
            msg = result.get(5, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (result != null){
                result.cancel(true);
            }
        }
        return msg;
    }

    @PostMapping("/setValue/{msg}")
    public void postValue(@PathVariable String msg) {
        asyncRequestService.postValue(msg);
    }
}