package com.fengx.mytest.base.thread.future;

import java.util.concurrent.*;

class App {
   private final static ExecutorService executor = Executors.newSingleThreadExecutor();
 private final static ArchiveSearcher searcher = (ArchiveSearcher) new ArchiveSearcherImpl();

   static void showSearch(final String target) throws InterruptedException {
     Future<String> future = executor.submit(new Callable<String>() {
         @Override
         public String call() {
             return searcher.search(target);
         }});
     // do other things while searching
    try {
     System.out.println("开始");
     Thread.sleep(3000);
     System.out.println("结束");
    } catch (Exception e) {
     e.printStackTrace();
    }
     try {
      System.out.println(future.get());
     } catch (ExecutionException ex) {
      ex.printStackTrace();
      return; }

     executor.shutdown();
   }

   public static void main(String[] args) throws InterruptedException {
     showSearch("hhhhhhhhhhhhhhhhh");
   }
}