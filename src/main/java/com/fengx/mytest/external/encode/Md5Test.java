package com.fengx.mytest.external.encode;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {

    private static String md5(byte[] data) {
        //得到md5的实例
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("生成md5失败");
            return "default";
        }
        //update的是byte类型的数组
        md.update(data);
        //加密后返回
        byte[] r = md.digest();
        return String.format("%032x", new BigInteger(1, r));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(md5("testsssssssssssssssssss".getBytes()));
        System.out.println(DigestUtils.md5Hex("testsssssssssssssssssss"));

        // e3d9197e0440ee37135ea966c475c1ea
        File file = new File("C:\\Users\\gzxzl\\Desktop\\java8路径.txt");
        try {
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            while (fis.read(buffer) > 0) {
                System.out.println(DigestUtils.md5Hex(buffer));
                System.out.println(md5(buffer));
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 大文件分批次加密
        System.out.println(DigestUtils.md5Hex(new FileInputStream(new File("C:\\Users\\gzxzl\\Desktop\\大文件-测试.mp4"))));
        FileInputStream f = new FileInputStream(new File("C:\\Users\\gzxzl\\Desktop\\大文件-测试.mp4"));
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] buffer = new byte[8192];
        int len;
        while (-1 != (len = f.read(buffer))) {
            digest.update(buffer, 0, len);
        }
        byte[] md5hash = digest.digest();
        System.out.println(String.format("%032x", new BigInteger(1, md5hash)));
    }
}
