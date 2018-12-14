package org.quickMap.Utils;

/*
 * @(#) EncrypAES.java
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 功能描述: 这里注意二进制转十六进制，十六进制转二进制的必要性

 * @version 2.4.0
 * @Copyright (c) 2002-2013 All rights reserved.
 * @create 2012-12-13 上午11:11:02
 **/


public class EncryptDes {

    private Logger logger = LoggerFactory.getLogger(EncryptDes.class);


    private SecretKeyFactory keyFactory;
    // 生成加密密钥
    private DESKeySpec keySpec;

    private SecretKey secretKey;

    private Cipher cipher;

    public EncryptDes(String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException{
        //KeySpec组成加密密钥的密钥内容的（透明）规范
        keySpec = new DESKeySpec(key.getBytes());
        keyFactory = SecretKeyFactory.getInstance("DES");
        // key的长度不能够小于8位字节
        secretKey = keyFactory.generateSecret(keySpec);
        cipher = Cipher.getInstance("DES");
    }

    /**
     * 字符串加密
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String Encrypt(String str) {

        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] src = str.getBytes();
            // 加密，结果保存进cipherByte
            byte[] encryByte = cipher.doFinal(src);
            String encryStr = parseByte2HexStr(encryByte);
            return encryStr;
        } catch (Exception e) {
            logger.error("加密异常",e);
            throw new IllegalArgumentException("加密异常");
        }
    }

    /**
     * 字符串解密
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String Decrypt(String str) {
        try {
            // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
            byte[] encryByte = parseHexStr2Byte(str);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(encryByte));
        } catch (Exception e) {
            logger.error("解密异常",e);
            throw new IllegalArgumentException("解密异常");
        }
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte[] buf) {
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

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}