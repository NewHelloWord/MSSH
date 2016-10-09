package com.sgl.weixin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {

    public static final String token = "yaoliaodetoken";

    public static boolean checkSign(String signature, String timestamp, String nonce){
        // 将token、timestamp、nonce三个参数进行字典序排序
        String[] str = new String[]{token,timestamp,nonce};
        Arrays.sort(str);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length ; i++) {
            sb = sb.append(str[i]);
        }
        MessageDigest md = null;
        String tempStr = null;
        try {
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            md = MessageDigest.getInstance("SHA-1");
            byte[] bt = md.digest(sb.toString().getBytes());
            tempStr = byteToStr(bt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tempStr != null ? tempStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}