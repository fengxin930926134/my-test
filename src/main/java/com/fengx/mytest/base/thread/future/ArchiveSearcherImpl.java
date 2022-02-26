package com.fengx.mytest.base.thread.future;

public class ArchiveSearcherImpl implements ArchiveSearcher {
    @Override
    public String search(String target) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "查询：" + target;
    }
}
