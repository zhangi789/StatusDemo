package com.status.cn;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kit.cn.common.RxUitl;
import com.status.cn.fragment.AddFragment;
import com.status.cn.fragment.DiscovyFragment;
import com.status.cn.fragment.MainFragment;
import com.status.cn.fragment.MsgFragment;
import com.status.cn.fragment.PersonalFragment;
import com.status.cn.util.RxStatusUtil;
import com.status.cn.view.GifView;
import com.status.cn.view.SlidingMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SlidingMenu mSlidingMenu;

    private ArrayList<Fragment> mFsList = new ArrayList<>();
    private TextView mMain_home, mMain_chat, mMain_life, mMain_personal;
    private ImageView mMain_add;


    private GifView mMenuBg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxUitl.setStatus(this);
        setContentView(R.layout.activity_main);


        initview();


        initdata();

    }

    private void initdata() {


        initTabFragment();

        mMenuBg.setMovieResource(R.drawable.timg);
    }


    private void initTabFragment() {
        mFsList.add(new MainFragment());
        mFsList.add(new DiscovyFragment());
        mFsList.add(new AddFragment());
        mFsList.add(new MsgFragment());
        mFsList.add(new PersonalFragment());

        changeFrag(0);

    }

    private void initview() {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
        mMain_home = (TextView) findViewById(R.id.main_home);
        mMain_chat = (TextView) findViewById(R.id.main_chat);
        mMain_add = (ImageView) findViewById(R.id.main_add);
        mMain_life = (TextView) findViewById(R.id.main_life);
        mMain_personal = (TextView) findViewById(R.id.main_personal);

        mMain_home.setOnClickListener(this);
        mMain_chat.setOnClickListener(this);
        mMain_add.setOnClickListener(this);
        mMain_life.setOnClickListener(this);
        mMain_personal.setOnClickListener(this);
        mMenuBg = (GifView) findViewById(R.id.add_gf_menu);

    }


    protected void hideFragment(Fragment currFragment) {
        if (currFragment == null)
            return;
        FragmentTransaction currFragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        currFragment.onPause();
        if (currFragment.isAdded()) {
            currFragmentTransaction.hide(currFragment);
            currFragmentTransaction.commitAllowingStateLoss();
        }
    }

    private void changeFrag(int curIndex) {
        for (int i = 0; i < mFsList.size(); i++) {
            if (i == curIndex) {
                showFragment(mFsList.get(i));
            } else {
                hideFragment(mFsList.get(i));
            }
        }
    }


    protected void showFragment(Fragment startFragment) {
        if (startFragment == null)
            return;
        FragmentTransaction startFragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (!startFragment.isAdded()) {
            startFragmentTransaction.add(R.id.fl_main_content, startFragment);
        } else {
            startFragment.onResume();
            startFragmentTransaction.show(startFragment);
        }
        startFragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.main_home:

                changeFrag(0);

                style(mMain_home, R.mipmap.tab_news_selected);
                style(mMain_life, R.mipmap.tab_find_normal);
                style(mMain_chat, R.mipmap.tab_battle_normal);
                style(mMain_personal, R.mipmap.tab_auxiliary_normal);

                break;
            case R.id.main_life:

                changeFrag(1);


                style(mMain_home, R.mipmap.tab_news_normal);
                style(mMain_life, R.mipmap.tab_find_selected);
                style(mMain_chat, R.mipmap.tab_battle_normal);
                style(mMain_personal, R.mipmap.tab_auxiliary_normal);

                break;
            case R.id.main_add:

                changeFrag(2);
                style(mMain_home, R.mipmap.tab_news_normal);
                style(mMain_life, R.mipmap.tab_find_normal);
                style(mMain_chat, R.mipmap.tab_battle_normal);
                style(mMain_personal, R.mipmap.tab_auxiliary_normal);

                break;
            case R.id.main_chat:

                changeFrag(3);
                style(mMain_home, R.mipmap.tab_news_normal);
                style(mMain_life, R.mipmap.tab_find_normal);
                style(mMain_chat, R.mipmap.tab_battle_selected);
                style(mMain_personal, R.mipmap.tab_auxiliary_normal);

                break;
            case R.id.main_personal:

                changeFrag(4);

                style(mMain_home, R.mipmap.tab_news_normal);
                style(mMain_life, R.mipmap.tab_find_normal);
                style(mMain_chat, R.mipmap.tab_battle_normal);
                style(mMain_personal, R.mipmap.tab_auxiliary_selected);

                break;


        }
    }
    /**
     * 导航栏TOP图标
     *
     * @param id
     * @param view
     */
    private void style(TextView view, int id) {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(null, ContextCompat.getDrawable(this, id), null, null);
    }
}
