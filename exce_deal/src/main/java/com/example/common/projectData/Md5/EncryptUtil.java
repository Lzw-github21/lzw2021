package com.example.common.projectData.Md5;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.UUID;

/**
 * @author yangsen
 * @date 2021/4/26 17:20
 */
@Component
public class EncryptUtil {

    public static void main(String[] args) throws Exception {
        EncryptUtil encryptUtil = new EncryptUtil();
        long time = System.currentTimeMillis();
//        Date date = DateUtils.parseDate("2021-04-01 12:00:00", "yyyy-MM-dd HH:mm:ss");
//        long time1 = date.getTime();
        // 监管端
//        String appkey = "c9406731-50bc-4e23-b3f5-b7e7d3093875";
//        String secret = "645ffda7-fc12-4415-a237-314d87d66716";
        // 项目端
//        String appkey = "34366638-3863-3636-2D62-336362D62643";
//        String secret = "34646639-3433-3533-2D62-335332D62643";
        String appkey = "b2d4c9a4-d881-11eb-a533-fa163e701da5";
        String secret = "b9600c53-d881-11eb-a533-fa163e701da5";
//        RH8f2WRpQh2FJT9BqU8BNg==

        String sign = encryptUtil.getSign(appkey + secret + "1624952049749");
        System.out.println(time);
        System.out.println(sign);
        System.out.println(encryptUtil.randomUUID());

    }

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
     * UUID生成唯一标识
     * @return
     */
    public String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }

    /***
     * AES 解密
     * @param content 密文
     * @param param
     * @return
     * @throws Exception
     */
    public String AES_Decrypt(String content, Map<String, String> param) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 密钥
        String key = param.get("key");
        // 偏移量
        String iv = param.get("iv");
        if (StringUtils.isBlank(key) || StringUtils.isBlank(content) || StringUtils.isBlank(iv)) {
            return content;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("ASCII"), "AES");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec IV = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, IV);
        byte[] buffer = new BASE64Decoder().decodeBuffer(content);
        byte[] encrypted = cipher.doFinal(buffer);
        // 此处使用BASE64做转码。
        return new String(encrypted, "UTF-8");
    }

    /***
     * AES 加密操作
     * @param param
     * @return
     */
    public String AES_Encrypt(Map<String, String> param) throws Exception {

        // 内容明文
        String content = param.get("content");
        // 密钥
        String key = param.get("key");
        // 偏移量
        String iv = param.get("iv");
        if (StringUtils.isBlank(key) || StringUtils.isBlank(content) || StringUtils.isBlank(iv)) {
            return content;
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
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes("UTF-8"));

            byte[] byteContent = content.getBytes("UTF-8");

            //使用加密秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            //SecretKeySpec skeySpec = getSecretKey(key);

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv); // 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent); // 加密

            return Base64Utils.encodeToString(result); // 通过Base64转码返回
        } catch (Exception ex) {
            throw new Exception("AES加密发生异常");
        }
    }

}
