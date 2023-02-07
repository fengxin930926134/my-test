package com.fengx.mytest.base.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;

/**
 * Linux用gcc生成动态链接库文件libhello.so
 * gcc -fPIC -I $JAVA_HOME/include -I $JAVA_HOME/include/linux -shared -o libhello.so hello.c
 */
public class Test {

    /**
     * 定义一个接口，默认的是继承Library ，如果动态链接库里的函数是以stdcall方式输出的，那么就继承StdCallLibrary
     * 这个接口对应一个动态链接(SO)文件
     */
    public interface LibraryAdd extends Library {
        // 这里使用绝对路径加载
        LibraryAdd LIBRARY_ADD = Native.load("User32", LibraryAdd.class);

//        /**
//         * 接口中只需要定义你要用到的函数或者公共变量，不需要的可以不定义sdf
//         * 映射libadd.so里面的函数，注意类型要匹配
//         */
//        int add(int a, int b);

        int MessageBoxA(Pointer pointer, WString content, WString title, int type);
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.library.path"));
//        // 调用so映射的接口函数
        int add = LibraryAdd.LIBRARY_ADD.MessageBoxA(null, new WString("123124"), null, 0);
        System.out.println("结果：" + add);
    }
}
