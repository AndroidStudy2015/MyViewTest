package com.example.a.myviewtest.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenHeight(Context context) {
        // 获取屏幕密度（方法2）
//        DisplayMetrics dm = new DisplayMetrics();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
//        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
//        float xdpi = dm.xdpi;
//        float ydpi = dm.ydpi;
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        return screenHeight;
    }
  public static int getScreenWidth(Context context) {
        // 获取屏幕密度（方法2）
//        DisplayMetrics dm = new DisplayMetrics();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
//        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
//        float xdpi = dm.xdpi;
//        float ydpi = dm.ydpi;
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        return screenWidth;
    }

}  