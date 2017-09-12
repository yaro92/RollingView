package com.ylye.rollingview.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.ylye.rollingview.MyApplication;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/8/15.
 * 提供dp、sp转换以及获取Res目录下的资源
 */
public class CommonUtils {
    /**
     * dip转化成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转化成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转化成sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转化成px
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 获取Resource对象
     */
    public static Resources getResources() {
        return MyApplication.getApplication().getResources();
    }

    /**
     * 获取Drawable资源
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取字符串资源
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取color资源
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取dimens资源
     */
    public static float getDimens(int resId) {
        return getResources().getDimension(resId);
    }

    /**
     * 获取字符串数组资源
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 转换成标准的, 2位小数格式
     * @param input  doubel型数据源
     * @return  标准2位小数形式的数据
     */
    public static String doubleFormatUtils2(String input) {
        double number = Double.parseDouble(input);
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(number);
        return format;
    }
    /**
     * 转换成标准的, 2位小数格式
     * @return  标准2位小数形式的数据
     */
    public static String doubleFormatUtils2(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(number);
        return format;
    }
}


