package com.ny.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密验证类
 *
 *
 */
public class Decript {

    public static String sha1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte b : messageDigest) {
                String shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Deprecated
    public static String sha(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(decript.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte b : messageDigest) {
                String shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Deprecated
    public static String md5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte b : md) {
                String shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return byte[]
     */
    @Deprecated
    public static byte[] encryptAES(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("AES");
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
            byte[] byteContent = new byte[0];
            try {
                byteContent = content.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 初始化
            try {
                if (cipher != null) {
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                }
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            // 加密
            try {
                if (cipher != null) {
                    try {
                        return cipher.doFinal(byteContent);
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return byte[]
     */
    @Deprecated
    public static byte[] decryptAES(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("AES");
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
            // 初始化
            try {
                if (cipher != null) {
                    cipher.init(Cipher.DECRYPT_MODE, key);
                }
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            // 加密
            try {
                try {
                    if (cipher != null) {
                        return cipher.doFinal(content);
                    }
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * BASE64解密
     *
     * @return String
     */
    @Deprecated
    public static String decryptBASE64() {
        return "";
    }

    /**
     * BASE64加密
     *
     * @return String
     */
    @Deprecated
    public static String encryptBASE64() {
        return "";
    }
}