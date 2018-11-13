package com.ny.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
public class ShaUtil {
    private static Logger logger = LoggerFactory.getLogger(ShaUtil.class);

    public static String encode(String param) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(param.getBytes());
            byte[] shaBytes = sha.digest();
            StringBuilder hexValue = new StringBuilder();
            for (byte b : shaBytes) {
                int val = ((int) b) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.toString());
        }
        return null;
    }
}
