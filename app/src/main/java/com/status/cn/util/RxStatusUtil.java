package com.status.cn.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @author 张海洋
 * @Date on 2019/01/14.
 * @org 上海相舆科技有限公司
 * @describe
 */
public class RxStatusUtil {
    /**
     * 版本判断沉浸式状态栏状态
     */
    public static void initStatus(final Activity mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //>21
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mContext.getWindow().setStatusBarColor(Color.TRANSPARENT);
                mContext.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                //小于21
                mContext.getWindow()
                        .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * setStatus
     * 占位符初始化布局后initview使用
     *
     * @param mView
     */
    public static void setStatus(View mView, Activity mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusHeight(mContext);
            ViewGroup.LayoutParams lp = mView.getLayoutParams();
            lp.height = statusBarHeight;
            mView.setLayoutParams(lp);
        }
    }

    /**
     * 设置状态高度
     *
     * @param mContext
     * @return
     */
    private static int getStatusHeight(Activity mContext) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
