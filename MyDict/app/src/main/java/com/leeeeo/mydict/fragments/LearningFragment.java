package com.leeeeo.mydict.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.models.EasyDictWords;
import com.leeeeo.mydict.models.EasyDictWordsManager;
import com.leeeeo.mydict.utils.WinToast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class LearningFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final static String TAG = LearningFragment.class.getSimpleName();
    private View mainView = null;

    private EditText et_show_words;
    private TextView tv_content;
    private Button btn_previous, btn_next, btn_random;

    private ArrayList<EasyDictWords> list = new ArrayList<>();
    private int currentPosition = 0;

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
        mainView = inflater.inflate(R.layout.fragment_learning, container, false);
        initSubViews();
        init();
        refreshData();
        return mainView;

    }

    private void init() {
        list.clear();
        list.addAll(EasyDictWordsManager.getInstance().list());

    }

    private void initSubViews() {
        btn_previous = (Button) mainView.findViewById(R.id.btn_previous);
        btn_next = (Button) mainView.findViewById(R.id.btn_next);
        btn_random = (Button) mainView.findViewById(R.id.btn_random);

        btn_random.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_previous.setOnClickListener(this);


        et_show_words = (EditText) mainView.findViewById(R.id.show_words);
        tv_content = (TextView) mainView.findViewById(R.id.trans_result);
    }

    public void refreshData() {
        init();
        showPosition(currentPosition);
    }

    public void showPosition(int pos) {
        btn_random.setClickable(true);
        btn_next.setClickable(true);
        btn_previous.setClickable(true);

        btn_next.setEnabled(true);
        btn_random.setEnabled(true);
        btn_previous.setEnabled(true);

        if (list.size() <= 0) {
            btn_next.setEnabled(false);
            btn_random.setEnabled(false);
            btn_previous.setEnabled(false);
            WinToast.toast(getActivity(), "当前词库中没有词汇,请先添加/导入/切换词库");
            return;
        }

        if (list.size() <= pos || pos < 0) {
            return;
        }
        if (pos == 0) {
            btn_previous.setClickable(false);
            btn_previous.setEnabled(false);
        }

        if (pos == list.size() - 1) {
            btn_next.setClickable(false);
            btn_next.setEnabled(false);
        }
        tv_content.setText(Html.fromHtml(list.get(pos).getExplains()));
        et_show_words.setText(Html.fromHtml(list.get(pos).getName_words()));
    }


    @Override
    public void onClick(View v) {
        init();
        Log.e("pos", "" + currentPosition + ":" + (list.size() - 1));
        if (list.size() <= 0) {
            btn_next.setEnabled(false);
            btn_random.setEnabled(false);
            btn_previous.setEnabled(false);
            WinToast.toast(getActivity(), "当前词库中没有词汇,请先添加/导入/切换词库");
            return;
        }

        switch (v.getId()) {
            case R.id.btn_next:
                if (currentPosition == list.size() - 1) {
                    WinToast.toast(getActivity(), "已经是最后一个了!");
                } else {
                    currentPosition++;

                    refreshData();
                }

                break;
            case R.id.btn_previous:
                if (currentPosition == 0) {
                    WinToast.toast(getActivity(), "已经是第一个了!");
                } else {
                    currentPosition--;
                    refreshData();
                }
                break;
            case R.id.btn_random:
                int tmp_pos = new Random().nextInt(list.size() - 1);
                if (tmp_pos == currentPosition) {
                    tmp_pos = new Random().nextInt(list.size() - 1);
                }
                currentPosition = tmp_pos;
                refreshData();
                break;
        }
    }
}
