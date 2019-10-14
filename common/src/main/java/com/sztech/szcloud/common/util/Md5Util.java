package com.sztech.szcloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @Company 杭州数政科技有限公司
 * @Author 有成
 * @Date 2019-03-13 10:50
 * @Description
 */
public class Md5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);


    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            LOGGER.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            LOGGER.error("not supported charset...{}", e);
            return s;
        }
    }

    public static String getSign(String str, String pkey) {
        String s = str + pkey;
        try {

            String sign = DigestUtils.md5DigestAsHex(s.getBytes("utf-8"));
            return sign;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5签名过程中出现错误");
        }
    }
}
