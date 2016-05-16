package com.leeeeo.mydict.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.leeeeo.mydict.R;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class ReviewFragment extends Fragment implements AdapterView.OnItemClickListener {

    private final static String TAG = ReviewFragment.class.getSimpleName();

    private View mainView;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_review, container, false);
        initSubViews();
        return mainView;

    }

    private void initSubViews() {

    }


}
