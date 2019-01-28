package com.status.cn.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.status.cn.R;
import com.status.cn.base.CommonBaseFragment;
import com.status.cn.view.GradationScrollView;

/**
 * Created by qihuiyou on 2017/6/2.
 * <p>
 * 我的
 */

public class MainFragment extends CommonBaseFragment implements View.OnClickListener{


    private GradationScrollView mScrollView;

    private ImageView btn_test;
    private Button btn_test2;
    private Button btn_test3;
    private LinearLayout mHome_ll_title;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, null);
        mScrollView = (GradationScrollView) inflate.findViewById(R.id.scrollview);
        btn_test = (ImageView) inflate.findViewById(R.id.btn_test);
        btn_test2 = (Button) inflate.findViewById(R.id.btn_test2);
        btn_test2.setOnClickListener(this);
        btn_test3 = (Button) inflate.findViewById(R.id.btn_test3);

        mHome_ll_title = (LinearLayout) inflate.findViewById(R.id.home_ll_title);
        return inflate;
    }

    /**
     * 获取顶部图片高度
     */
    private void initListeners() {

        ViewTreeObserver vto = btn_test.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setGradationListener(btn_test.getHeight());
                btn_test.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    /**
     * 设置动态监听
     *
     * @param imageHeight
     */
    private void setGradationListener(final int imageHeight) {

        mScrollView.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {   //设置标题的背景颜色
                    mHome_ll_title.setBackgroundColor(Color.argb((int) 0, 62, 144, 227));

                    Log.i("GGG", "mScrollView 1");
                } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    mHome_ll_title.setBackgroundColor(Color.argb((int) alpha, 62, 144, 227));

                    Log.i("GGG", "mScrollView 2");
                } else {    //滑动到banner下面设置普通颜色

                    mHome_ll_title.setBackgroundColor(Color.argb((int) 0, 62, 144, 227));

                    Log.i("GGG", "mScrollView 3");
                }
            }
        });
    }

    @Override
    protected void initData() {
        initListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_test2:
                Toast.makeText(mContext,"fdfs",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
