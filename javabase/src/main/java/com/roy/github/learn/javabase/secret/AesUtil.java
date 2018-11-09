package com.roy.github.learn.javabase.secret;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Calendar;

/**
 * Created by roy on 2018/10/30.
 */
public class AesUtil {
    private final static String encoding = "UTF-8";
    private static String PASSWORD = "s2_I$-!#eu_3y2*4D-8^2{A3R_E}I5%&U#I@-O;!";
    // static {
    // ResourceBundle resource = ResourceBundle.getBundle("config");
    // PASSWORD = resource.getString("PASSWORD");
    // }

    public static void main(String[] args) throws Exception {
        String me = "CAT0001";
        String content = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String token = me + "," + content;
        token = "CAT0001,1478842909958";
        System.out.println("加密字符：" + token);
        System.out.println("加密后的字符：" + aesEncrypt(token));
        System.out.println("解密后的字符：" + aesDecrypt(aesEncrypt(token)));
    }

    public static String aesEncrypt(String content) throws Exception {
        byte[] encryptResult = encrypt(content);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        // BASE64位加密
        encryptResultStr = ebotongEncrypto(encryptResultStr);
        return encryptResultStr;
    }


    public static String aesDecrypt(String encryptResultStr) throws Exception {
        // BASE64位解密
        String decrpt = ebotongDecrypto(encryptResultStr);
        byte[] decryptFrom = parseHexStr2Byte(decrpt);
        byte[] decryptResult = decrypt(decryptFrom);
        return new String(decryptResult);
    }


    public static String ebotongEncrypto(String str) throws Exception {
        String result = str;
        if (str != null && str.length() > 0) {
            byte[] encodeByte = str.getBytes(encoding);
            byte[] debytes = Base64.encodeBase64Chunked(encodeByte);
            result = new String(debytes);
            // base64加密超过一定长度会自动换行 需要去除换行符
            return result.replaceAll("\r\n", "").replaceAll("\r", "")
                    .replaceAll("\n", "");
        }
        return result;
    }


    public static String ebotongDecrypto(String str) throws Exception {
        byte[] encodeByte = Base64.decodeBase64(str);
        return new String(encodeByte);
    }


    private static byte[] encrypt(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(PASSWORD.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(byteContent);
        return result; // 加密
    }


    private static byte[] decrypt(byte[] content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(PASSWORD.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(content);
        return result; // 加密
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
