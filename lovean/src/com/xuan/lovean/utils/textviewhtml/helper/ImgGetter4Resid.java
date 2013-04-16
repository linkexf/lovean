/* 
 * @(#)ImgGetter4Resid.java    Created on 2013-4-16
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package com.xuan.lovean.utils.textviewhtml.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;

/**
 * 加载id的资源图片
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-4-16 下午6:05:50 $
 */
public class ImgGetter4Resid implements ImageGetter {

    private final Context context;

    public ImgGetter4Resid(Context context) {
        this.context = context;
    }

    @Override
    public Drawable getDrawable(String source) {
        int resid = Integer.valueOf(source);
        Drawable drawable = context.getResources().getDrawable(resid);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

}
