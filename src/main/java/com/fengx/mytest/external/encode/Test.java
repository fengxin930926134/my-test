package com.fengx.mytest.external.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String str = "\u53d6";
//        System.out.println(str);
        System.out.println(getEncoding("测试"));
//        JSON.toJSONString(s, SerializerFeature.BrowserCompatible)
        String unicode = new String("测试".getBytes(),"UTF-8");
        System.out.println(unicode);

        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println(decodeUnicode("\\u53d6"));
        System.out.println(new String("啊啊啊".getBytes(),"gbk"));
        System.out.println(new String("啊啊啊".getBytes(),"utf-8"));
        System.out.println(new String("啊啊啊".getBytes(),"gb2312"));
        System.out.println(gbEncoding(new String("啊啊啊".getBytes(),"gb2312")));
        System.out.println("\\"+gbEncoding("取"));;
        try{
            String str = "\u53d6";
            System.out.println(str);
            str = new String(str.getBytes(),"gb2312");
            System.out.println(str);
        }catch(UnsupportedEncodingException e){
        }

        System.out.println("%D6%D0%CE%C4%CC%F9");
        System.out
                .println(URLDecoder.decode("%D6%D0%CE%C4%B9%FA%BC%CA", "GBK"));// GBK编码转中文
        System.out.println(URLEncoder.encode("中文贴", "GB2312")); // 中文编码转GBK
        System.out.println(URLDecoder.decode(
                "%E4%B8%AD%E6%96%87%E5%9B%BD%E9%99%85", "UTF-8"));// UTF-8编码转中文
        System.out.println(URLEncoder.encode("中文国际", "UTF-8")); // UTF-8编码转中文
    }

    /**
     * 获取编码格式
     * @param str
     * @return
     */
    public static String getEncoding(String str)
    {
        String encode;

        encode = "UTF-16";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "ASCII";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
            }
        }
        catch(Exception ex) {}

        encode = "ISO-8859-1";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "GB2312";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "UTF-8";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        /*
         *......待完善
         */

        return "未识别编码格式";
    }
    /*
     * 中文转unicode编码
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }
    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
