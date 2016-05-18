package com.leeeeo.mydict.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.leeeeo.mydict.R;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class QueryingFragment extends Fragment implements AdapterView.OnItemClickListener {

    private final static String TAG = QueryingFragment.class.getSimpleName();
    private View mainView = null;
    private TextView tv_query = null;

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
        mainView = inflater.inflate(R.layout.fragment_querying, container, false);
        initSubViews();
        return mainView;


    }

    private void initSubViews() {
        tv_query = (TextView) mainView.findViewById(R.id.query_result);
        tv_query.setText(Html.fromHtml("<h1>有道释义:</h1><hr><br/>英[hə'ləʊ]<br/><br/>" +
                        "美[həˈloʊ]<br/><br/>" +
                        "int.  打招呼; 哈喽，喂; 你好，您好; 表示问候;<br/><br/>" +
                        "n. “喂”的招呼声或问候声;<br/><br/>" +
                        "vi. 喊“喂”;<br/><br/>" +
                        "[例句]Hello, Trish.<br/><br/>" +
                        "你好，特里茜。<br/><br/>" +
                        "[其他]  复数：hellos<br/><br/>"
        ));
    }
}
