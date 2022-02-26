package com.fengx.mytest.base.thread.future;

import java.util.concurrent.*;

public class App2 {
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
    private final static ArchiveSearcher searcher = new ArchiveSearcherImpl();
    public static void main(String[] args) {
        FutureTask<String> future = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() {
                return searcher.search("target");
            }
        });
        executor.execute(future);
        System.out.println("前面");
        try {
            System.out.println(future.get(1, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("后面");
        executor.shutdown();
    }
}
