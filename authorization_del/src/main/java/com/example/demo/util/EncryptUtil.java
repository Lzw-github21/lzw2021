package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author yangsen
 * @date 2021/8/4 14:37
 */
@Slf4j
@Component
public class EncryptUtil {

    final String AES_KEY = "xRH8EVswh6WdG5IC";
    final String AES_IV = "LAxl9zHb4kBvzEDa";

    /**
     * 生成签名
     * <p>
     * 使用md5加密，再base64摘要
     *
     * @param value 明文
     * @return 签名
     * @throws Exception
     */
    public String getSign(String value) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.encodeBase64(md.digest()), StandardCharsets.UTF_8);
    }

    /**
     * AES 加密操作
     *
     * @param plainText 内容明文
     * @return
     * @throws Exception
     */
    public String AESEncrypt(String plainText) {
        try {
            return this.AESEncrypt(plainText, AES_KEY, AES_IV);
        } catch (Exception e) {
            log.error("AES 加密错误！" + e.getMessage());
            e.printStackTrace();
            return plainText;
        }
    }

    /**
     * AES 加密操作
     *
     * @param plainText 内容明文
     * @param key       密钥
     * @param iv        偏移量
     * @return
     * @throws Exception
     */
    public String AESEncrypt(String plainText, String key, String iv) throws Exception {

        if (StringUtils.isBlank(key) || StringUtils.isBlank(plainText) || StringUtils.isBlank(iv)) {
            return plainText;
        }

        try {
            /*
             * 新建一个密码编译器的实例，由三部分构成，用"/"分隔，分别代表如下
             * 1. 加密的类型(如AES，DES，RC2等)
             * 2. 模式(AES中包含ECB，CBC，CFB，CTR，CTS等)
             * 3. 补码方式(包含nopadding/PKCS5Padding等等)
             * 依据这三个参数可以创建很多种加密方式
             */
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //偏移量
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            byte[] byteContent = plainText.getBytes(StandardCharsets.UTF_8);

            //使用加密秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            //SecretKeySpec skeySpec = getSecretKey(key);

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv); // 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent); // 加密

            return Base64Utils.encodeToString(result); // 通过Base64转码返回
        } catch (Exception ex) {
            throw new Exception("AES加密发生异常");
        }
    }

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @return
     * @throws Exception
     */
    public String AESDecrypt(String cipherText) {
        try {
            return this.AESDecrypt(cipherText, AES_KEY, AES_IV);
        } catch (Exception e) {
            log.error("AES 解密错误！" + e.getMessage());
            e.printStackTrace();
            return cipherText;
        }
    }

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @param key        密钥
     * @param iv         偏移量
     * @return
     * @throws Exception
     */
    public String AESDecrypt(String cipherText, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 密钥
//        String key = param.get("key");
        // 偏移量
//        String iv = param.get("iv");
        if (StringUtils.isBlank(key) || StringUtils.isBlank(cipherText) || StringUtils.isBlank(iv)) {
            return cipherText;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "AES");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec IV = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, IV);
        byte[] buffer = new BASE64Decoder().decodeBuffer(cipherText);
        byte[] encrypted = cipher.doFinal(buffer);
        // 此处使用BASE64做转码。
        return new String(encrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        EncryptUtil encryptUtil = new EncryptUtil();
        System.out.println(encryptUtil.AESEncrypt("lizhiwei"));

        System.out.println(encryptUtil.AESDecrypt("hBa4YdfsjxZIJ/kR9pkJ5g=="));
    }

}
