package com.example.second.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

/**
 * TokenUtil
 *
 * @author hf <liamxin@yeah.net>
 * @version 0.1
 * @since 2020/9/24 2:24 下午
 */

public class TokenUtil {

    private final static Random random;

    private final static String cipher;

    private final static byte[] dict;

    private final static String[] num;

    static {
        random = new Random();
        cipher = "krcmf_appointment";
        dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+ ".getBytes();
        num = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    }

    /**
     * 生成token
     *
     * @return token
     */
    public static String generate() {
        String encode = DigestUtils.sha1Hex(getRandomByte(16)) + System.currentTimeMillis();
        return DigestUtils.md5Hex(encode + cipher);
    }


    /**
     * 生成随机数字码
     *
     * @param len 长度
     * @return 随机码
     */
    public static String generateCode(int len) {
        int           size = num.length;
        StringBuilder sb   = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(num[random.nextInt(size)]);
        }
        return sb.toString();
    }

    private static byte[] getRandomByte(int len) {
        int    size  = dict.length;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = dict[random.nextInt(size)];
        }
        return bytes;
    }


}
