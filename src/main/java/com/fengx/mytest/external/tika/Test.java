package com.fengx.mytest.external.tika;

import org.apache.tika.Tika;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\gzxzl\\Desktop\\服务器部署项目情况.xlsx");
        Tika tika = new Tika();
        String filetype = tika.detect(new FileInputStream(file), "服务器部署项目情况.xlsx");
        System.out.println(filetype);

        FileInputStream inputFile = new FileInputStream("C:\\Users\\gzxzl\\Desktop\\服务器部署项目情况.xlsx");
        String mimeType = URLConnection.guessContentTypeFromStream(new BufferedInputStream(inputFile));
        System.out.println(mimeType);
    }
}
