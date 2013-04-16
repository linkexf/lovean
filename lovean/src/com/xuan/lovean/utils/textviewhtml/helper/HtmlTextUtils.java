/* 
 * @(#)TextUtils.java    Created on 2013-4-16
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package com.xuan.lovean.utils.textviewhtml.helper;

/**
 * html拼接的一些工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-4-16 下午6:12:08 $
 */
public abstract class HtmlTextUtils {

    /**
     * 把本地的resid拼接成html
     * 
     * @param resid
     * @return
     */
    public static String getImageHtmlByResid(int resid) {
        return "<img src='" + resid + "'>";
    }

}
