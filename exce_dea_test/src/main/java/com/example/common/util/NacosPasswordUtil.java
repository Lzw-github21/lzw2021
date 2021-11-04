package com.example.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lizhiwei
 * @date 2021/11/4 9:17
 */
public class NacosPasswordUtil {

    public static void main(String[] args) {
        String encryptPas = getEncryptPassword("lzw");
        System.out.println(encryptPas);
        System.out.println("$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu".equals("$10$mHtDWSPVjHedpvCgaZxkjOBtLUJWrD0DDsSGd4izLsjwOMguwBrJi"));
    }

    /**
     * nacos用户明文密码转加密密文的工具类方法
     *
     * @param password
     * @return
     */
    public static String getEncryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
