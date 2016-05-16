package com.leeeeo.mydict.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class ContentFragmentAdapter extends FragmentStatePagerAdapter {
    public Context mContext;
    // 填充主界面的view
    List<Fragment> fragmentList;

    public ContentFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
