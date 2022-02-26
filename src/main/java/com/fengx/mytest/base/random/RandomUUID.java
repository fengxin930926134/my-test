package com.fengx.mytest.base.random;

import java.util.UUID;

public class RandomUUID {

    private static final String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成短UUID（8位）
     */
    public static String generateShortUuid() {
        return generateUuid(0);
    }

    /**
     * 指定位数的UUID 最少8位且偶数
     *
     * @param figure 位
     * @return UUID
     */
    public static String generateUuid(int figure) {
        // 基础最低位数
        int subLen = 8;
        if (figure < subLen) {
            figure = subLen;
        }
        if (figure % subLen > 0) {
            figure--;
        }
        StringBuilder shortBuffer = new StringBuilder();
        int num = figure / subLen;
        for (int i = 0; i < num; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            for (int j = 0; j < subLen; j++) {
                String str = uuid.substring(j * 4, j * 4 + 4);
                int x = Integer.parseInt(str, 16);
                shortBuffer.append(CHARS[x % 0x3E]);
            }
        }
        return shortBuffer.toString();
    }

}
