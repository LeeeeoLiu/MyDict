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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.models.EasyDictWords;
import com.leeeeo.mydict.models.EasyDictWordsManager;
import com.leeeeo.mydict.utils.WinToast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class VerifyingFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final static String TAG = VerifyingFragment.class.getSimpleName();

    private View mainView;
    private ArrayList<EasyDictWords> list = new ArrayList<>();
    private int currentPosition = 0;
    private Button btn_previous;
    private Button btn_next;
    private TextView explaination;
    private EditText et_show_words;
    private RadioButton[] radioButtons = new RadioButton[4];
    private EditText question_words;
    private RadioGroup radioGroup;
    private int[] arr = new int[4];


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
        mainView = inflater.inflate(R.layout.activity_exam, container, false);
        initSubViews();

        return mainView;
    }

    private void init() {
        list.clear();
        list.addAll(EasyDictWordsManager.getInstance().list());

    }

    private void initSubViews() {
        btn_previous = (Button) mainView.findViewById(R.id.btn_previous);
        btn_next = (Button) mainView.findViewById(R.id.btn_next);
        explaination = (TextView) mainView.findViewById(R.id.explaination);
        question_words = (EditText) mainView.findViewById(R.id.question);
        radioGroup = (RadioGroup) mainView.findViewById(R.id.radioGroup);

        btn_next.setOnClickListener(this);
        btn_previous.setOnClickListener(this);

        radioButtons[0] = (RadioButton) mainView.findViewById(R.id.answerA);
        radioButtons[1] = (RadioButton) mainView.findViewById(R.id.answerB);
        radioButtons[2] = (RadioButton) mainView.findViewById(R.id.answerC);
        radioButtons[3] = (RadioButton) mainView.findViewById(R.id.answerD);

        radioGroup.setOnCheckedChangeListener(mChangeRadio);
//        tv_content = (TextView) mainView.findViewById(R.id.trans_result);
    }

    private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == radioButtons[arr[0]].getId()) {
                WinToast.toast(getActivity(), "回答正确！请点击“下一题");
                btn_next.setEnabled(true);
            } else {
                WinToast.toast(getActivity(), "回答错误！");
            }
        }
    };

    public void refreshData() {
        init();
        showPosition(currentPosition);
    }

    public void showPosition(int pos) {
        btn_next.setClickable(true);
        btn_previous.setClickable(true);

        btn_next.setEnabled(true);
        btn_previous.setEnabled(true);

        if (list.size() <= 0) {
            btn_next.setEnabled(false);
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
//        radioButtons[0].setText(q.answerA);
//        radioButtons[1].setText(q.answerB);
//        radioButtons[2].setText(q.answerC);
//        radioButtons[3].setText(q.answerD);

        int right_answer = new Random().nextInt(4);
        Log.e("random", "random:" + right_answer);

        arr[0] = new Random().nextInt(4);
        int i = 1;
        //外循环定义四个数
        while (i <= 3) {
            int x = new Random().nextInt(4);
            /*内循环：新生成随机数和已生成的比较，
             *相同则跳出内循环，再生成一个随机数进行比较
             *和前几个生成的都不同则这个就是新的随机数
            */
            for (int j = 0; j <= i - 1; j++) {
                //相同则跳出内循环，再生成一个随机数进行比较
                if (arr[j] == x) {
                    break;
                }
                //执行完循环和前几个生成的都不同则这个就是新的随机数
                if (j + 1 == i) {
                    arr[i] = x;
                    i++;
                }
            }
        }
        radioButtons[arr[0]].setText(Html.fromHtml(list.get(pos).getExplains()));
        String answer;
        int tmp_pos = new Random().nextInt(list.size() - 1);
        answer=list.get(tmp_pos).getExplains();
        radioButtons[arr[1]].setText(Html.fromHtml(list.get(tmp_pos).getExplains()));
        tmp_pos = new Random().nextInt(list.size() - 1);
        radioButtons[arr[2]].setText(Html.fromHtml(list.get(tmp_pos).getExplains()));
        tmp_pos = new Random().nextInt(list.size() - 1);
        radioButtons[arr[3]].setText(Html.fromHtml(list.get(tmp_pos).getExplains()));
        question_words.setText(Html.fromHtml(list.get(pos).getName_words()));
    }


    @Override
    public void onClick(View v) {
        init();
        Log.e("pos", "" + currentPosition + ":" + (list.size() - 1));
        if (list.size() <= 0) {
            btn_next.setEnabled(false);
            btn_previous.setEnabled(false);
            WinToast.toast(getActivity(), "当前词库中没有词汇,请先添加/导入/切换词库");
            return;
        }

        switch (v.getId()) {
            case R.id.btn_next:
                btn_next.setText("下一题");
                if (currentPosition == list.size() - 1) {
                    WinToast.toast(getActivity(), "已经是最后一个了!");
                } else {
                    currentPosition++;
                    radioGroup.clearCheck();
                    refreshData();
                }

                break;
            case R.id.btn_previous:
                if (currentPosition == 0) {
                    WinToast.toast(getActivity(), "已经是第一个了!");
                } else {
                    currentPosition--;
                    radioGroup.clearCheck();
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
