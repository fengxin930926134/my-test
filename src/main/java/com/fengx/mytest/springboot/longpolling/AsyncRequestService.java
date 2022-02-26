package com.fengx.mytest.springboot.longpolling;



import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncRequestService {

    private String msg = null;

    @Async
    public Future<String> getValue() throws InterruptedException {

        while (true){
            synchronized (this){
                if (msg != null){
                    String resultMsg = msg;
                    msg = null;
                    return new AsyncResult<>(resultMsg);
                }
            }
            Thread.sleep(100);
        }
    }

    public synchronized void postValue(String msg) {
        try {
            Thread.sleep(2000);
            this.msg = msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}