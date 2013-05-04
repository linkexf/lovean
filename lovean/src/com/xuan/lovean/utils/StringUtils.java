/* 
 * @(#)StringUtil.java    Created on 2012-9-20
 * Copyright (c) 2012 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package com.xuan.lovean.utils;

import java.io.UnsupportedEncodingException;

import android.text.TextUtils;

/**
 * 字符串工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-3-13 下午2:18:14 $
 */
public abstract class StringUtils {

    /**
     * 编码UTF-8
     * 
     * @param str
     * @return
     */
    public static String encodeToUTF8(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }

        return newString(StringUtils.getBytes(str, "iso8859-1"), "utf-8");
    }

    /**
     * 字节数组转String
     * 
     * @param bs
     * @param charset
     * @return
     */
    public static String newString(byte[] bs, String charset) {
        try {
            String str = new String(bs, charset);
            return str;
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String转字节数组，图片要用：ISO8859-1编码
     * 
     * @param str
     * @param charsetName
     * @return
     */
    public static byte[] getBytes(String str, String charsetName) {
        try {
            return str.getBytes(charsetName);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 截取固定长度的字符串，超长部分用suffix代替，最终字符串真实长度不会超过maxLength.
     * 
     * @param str
     * @param maxLength
     * @param suffix
     * @return
     */
    public static String cutOut(String str, int maxLength, String suffix) {
        if (Validators.isEmpty(str)) {
            return str;
        }

        int byteIndex = 0;
        int charIndex = 0;

        while (charIndex < str.length() && byteIndex <= maxLength) {
            char c = str.charAt(charIndex);
            if (c >= 256) {
                byteIndex += 2;
            }
            else {
                byteIndex++;
            }
            charIndex++;
        }

        if (byteIndex <= maxLength) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, charIndex));
        sb.append(suffix);

        while (getRealLength(sb.toString()) > maxLength) {
            sb.deleteCharAt(--charIndex);
        }

        return sb.toString();
    }

    /**
     * 取得字符串的真实长度，一个汉字长度为两个字节。
     * 
     * @param str
     *            字符串
     * @return 字符串的字节数
     */
    public static int getRealLength(String str) {
        if (str == null) {
            return 0;
        }

        char separator = 256;
        int realLength = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= separator) {
                realLength += 2;
            }
            else {
                realLength++;
            }
        }
        return realLength;
    }

}
