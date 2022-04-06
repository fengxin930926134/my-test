package com.fengx.mytest.base.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\gzxzl\\Desktop\\ceshi.zip");
        // 正确操作需要先通过mkdirs创建目录，再通过createNewFile创建文件
        if (!file.exists() && !file.createNewFile()) {
            throw new Exception("创建文件失败！");
        }
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(file));
        // 携带目录则压缩的时候会保存在此目录
        zip.putNextEntry(new ZipEntry("test1/b.txt"));
        FileInputStream zipSource = new FileInputStream(new File("C:\\Users\\gzxzl\\Desktop\\test\\test1\\b.txt"));
        BufferedInputStream bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
        int read;
        byte[] buf = new byte[1024 * 10];
        while ((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
            zip.write(buf, 0, read);
        }

        zip.putNextEntry(new ZipEntry("a.txt"));
        FileInputStream zipSource1 = new FileInputStream(new File("C:\\Users\\gzxzl\\Desktop\\test\\a.txt"));
        BufferedInputStream bufferStream1 = new BufferedInputStream(zipSource1, 1024 * 10);
        buf = new byte[1024 * 10];
        while ((read = bufferStream1.read(buf, 0, 1024 * 10)) != -1) {
            zip.write(buf, 0, read);
        }

        zip.finish();
        zip.close();
    }
}
