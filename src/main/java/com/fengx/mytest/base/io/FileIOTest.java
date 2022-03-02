package com.fengx.mytest.base.io;

import java.io.File;
import java.io.FileWriter;

/**
 * file io test
 */
public class FileIOTest {
    public static void main(String[] args) {
        // FileInputStream
//        try {
//            File file = new File("C:\\Users\\gzxzl\\Desktop\\cmd.txt");
//            if (!file.exists()) {
//                throw new Exception("不存在");
//            }
//            InputStream in = new FileInputStream(file);
//            //byte[] buffer = new byte[1024];
//            byte[] buffer = new byte[in.available()];
//            //while (in.read(buffer, 0, buffer.length) != -1) {
//            //    System.out.println(new String(buffer));
//            //}
//
//            while (in.read(buffer) != -1) {
//                System.out.println(new String(buffer));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // FileOutputStream
//        try {
//            File file = new File("C:\\Users\\gzxzl\\Desktop\\aa.txt");
//            // 正确操作需要先通过mkdirs创建目录，再通过createNewFile创建文件
//            if (!file.exists() && !file.createNewFile()) {
//                throw new Exception("创建文件失败！");
//            }
//            OutputStream out = new FileOutputStream(file);
//            byte[] buffer = "aaaaaaaa".getBytes();
//            out.write(buffer);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // FileReader
//        try {
//            File file = new File("C:\\Users\\gzxzl\\Desktop\\cmd.txt");
//            // 正确操作需要先通过mkdirs创建目录，再通过createNewFile创建文件
//            if (!file.exists() && !file.createNewFile()) {
//                throw new Exception("创建文件失败！");
//            }
//            // FileReader fileReader = new FileReader(file);
//            // 多一个readLine方法
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String str;
//            while ((str = br.readLine()) != null) {
//                System.out.println(str);
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            File file = new File("Hello1.txt");
            // 创建文件
            file.createNewFile();
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);
            // 向文件写入内容
            writer.write("This\n is\n an\n example\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
