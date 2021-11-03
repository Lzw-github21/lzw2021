package com.example.common.projectData.Md5;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class MD5Test {
    public static String md5(String str) throws Exception {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            System.out.println("md5对象：" + md5);
            System.out.println("原始数据：" + str);

            byte[] output = md5.digest(str.getBytes());
            String str_output = new String(output);
            System.out.println("加密后数据：" + str_output);

            str_output = Base64.encodeBase64String(output);
            System.out.println("处理后的加密数：" + str_output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        MD5Test.md5("dd");
        EncryptUtil encryptUtil = new EncryptUtil();
    }


}
