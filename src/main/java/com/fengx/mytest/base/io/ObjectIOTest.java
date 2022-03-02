package com.fengx.mytest.base.io;

import lombok.Data;

import java.io.*;

public class ObjectIOTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //創建對象輸出流，在使用對象輸出流時候必須進行對象序列化，否知會報錯java.io.NotSerializableException
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt",false));
        //創建一個persn對象
        Person p = new Person();
        p.setAge(1);
        p.setName("哈发服务");
        //寫出對象流
        oos.writeObject(p);
        //關閉流
        oos.flush();
        oos.close();


        ObjectInputStream ois  = new ObjectInputStream(new FileInputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt"));
        Person person  = (Person)ois.readObject();
        System.out.println(person);
        ois.close();
    }

    @Data
    static class Person implements Serializable {

       private String name;
       private Integer age;
    }
}
