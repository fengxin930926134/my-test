package com.fengx.mytest.base.fileio;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        // 名字转换
        File file1 = new File("D:\\data\\00a779b7565d41bfede777550bf2abee\\系统架构设计师考试32小时通关.pdf");
        File file2 = new File("D:\\data\\test.pdf");
        if (file1.exists()) {
            boolean b = file1.renameTo(file2);
            System.out.println(b);
        }
    }
}
