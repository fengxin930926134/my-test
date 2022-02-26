package com.fengx.mytest.external.picture;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片压缩
 */
public class Compress {

  public static void main(String[] args) throws Exception {
    // 0.4x + 0.95y = 2000
//    Thumbnails.of(new File("C:\\Users\\gzxzl\\Desktop\\隆正良.JPG"))
//        .scale(1f) //图片大小（长宽）压缩比例 从0-1，1表示原图
//        .outputQuality(0.99f) //图片质量压缩比例 从0-1，越接近1质量越好
//        .toOutputStream(new FileOutputStream("C:\\Users\\gzxzl\\Desktop\\隆正良_10.jpg"));

    // 0.6x + 0.97y = 840
//    Thumbnails.of(new File("C:\\Users\\gzxzl\\Desktop\\农蓬荷.jpg"))
//            .scale(0.6f) //图片大小（长宽）压缩比例 从0-1，1表示原图
//            .outputQuality(0.97f) //图片质量压缩比例 从0-1，越接近1质量越好
//            .toOutputStream(new FileOutputStream("C:\\Users\\gzxzl\\Desktop\\农蓬荷_51.jpg"));


    // z = 4
    // y = 4747.25
    // x = -6274.71875
    // a = (size - 0.4 * x) / y

    long size = 900000;
    float outputQuality = 1f;
    float scale = 1f;

    if (size > 800000) {
      scale = 0.2f;
      outputQuality = 0.91f;
    } else if (size > 700000) {
      scale = 0.2f;
      outputQuality = 0.93f;
    } else if (size > 600000) {
      scale = 0.2f;
      outputQuality = 0.94f;
    } else if (size > 400000) {
      scale = 0.2f;
      outputQuality = 0.96f;
    } else if (size > 200000) {
      scale = 0.2f;
      outputQuality = 0.98f;
    } else if (size > 100000) {
      scale = 0.2f;
      outputQuality = 0.99f;
    } else if (size > 80000) {
      scale = 0.3f;
      outputQuality = 0.99f;
    } else if (size > 50000) {
      scale = 0.5f;
      outputQuality = 0.99f;
    }

    Thumbnails.of(new File("C:\\Users\\gzxzl\\Desktop\\70.jpg"))
            .scale(scale)
            .outputQuality(outputQuality)
            .toOutputStream(new FileOutputStream("C:\\Users\\gzxzl\\Desktop\\隆正良1.jpg"));

  }

}