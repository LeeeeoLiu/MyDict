package com.leeeeo.mydict.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.adapters.ContentFragmentAdapter;
import com.leeeeo.mydict.apps.AppEngine;
import com.leeeeo.mydict.fragments.LearningFragment;
import com.leeeeo.mydict.fragments.LibraryFragment;
import com.leeeeo.mydict.fragments.QueryingFragment;
import com.leeeeo.mydict.fragments.SettingFragment;
import com.leeeeo.mydict.fragments.VerifyingFragment;
import com.leeeeo.mydict.views.CustomerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 16/5/16.
 * Email: Jacob.Deng@about-bob.com
 */
public class MainTabActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener {

    private CustomerViewPager mViewPager;

    private ContentFragmentAdapter mContentFragmentAdapter;


    private List<Fragment> fragmentList;


    private QueryingFragment queryingFragment;
    private LearningFragment learningFragment;

    private VerifyingFragment verifyingFragment;
    private LibraryFragment libraryFragment;
    private SettingFragment settingFragment;

    private TextView topTitle;
    private ImageView backView;
    private ImageView addView;

    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintab);
        AppEngine.getInstance().setCurrentContext(this);
        initSubViews();

    }


    private void initSubViews() {

        mViewPager = (CustomerViewPager) findViewById(R.id.viewpager_content);
        mViewPager.addOnPageChangeListener(this);

        fragmentList = new ArrayList<Fragment>();

        queryingFragment = new QueryingFragment();
        learningFragment = new LearningFragment();
        settingFragment = new SettingFragment();
        verifyingFragment = new VerifyingFragment();

        fragmentList.add(queryingFragment);
        fragmentList.add(learningFragment);
        fragmentList.add(verifyingFragment);
        fragmentList.add(settingFragment);

        topTitle = (TextView) findViewById(R.id.top_menu_title);
        backView = (ImageView) findViewById(R.id.top_menu_back_btn);
        addView = (ImageView) findViewById(R.id.top_menu_right_btn);

        layout1 = (LinearLayout) findViewById(R.id.id_tab_query);
        layout2 = (LinearLayout) findViewById(R.id.id_tab_learning);
        layout3 = (LinearLayout) findViewById(R.id.id_tab_verify);
        layout4 = (LinearLayout) findViewById(R.id.id_tab_setting);


        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);

        mContentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager(), fragmentList);

        mViewPager.setAdapter(mContentFragmentAdapter);

        mViewPager.setCurrentItem(0);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_query:
                mViewPager.setCurrentItem(0);
                topTitle.setText("查询单词");
                break;
            case R.id.id_tab_learning:
                mViewPager.setCurrentItem(1);
                topTitle.setText("学习单词");
                break;
            case R.id.id_tab_verify:
                mViewPager.setCurrentItem(2);
                topTitle.setText("单词测试");
                break;
            case R.id.id_tab_setting:
                mViewPager.setCurrentItem(3);
                topTitle.setText("系统设置");
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                //mViewPager.setCurrentItem(0);
                topTitle.setText("查询单词");
                break;
            case 1:
                //mViewPager.setCurrentItem(1);
                topTitle.setText("学习单词");
                break;
            case 2:
                //mViewPager.setCurrentItem(2);
                topTitle.setText("单词测试");
                break;
            case 3:
                //mViewPager.setCurrentItem(3);
                topTitle.setText("系统设置");
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
