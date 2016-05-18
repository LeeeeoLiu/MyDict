package com.leeeeo.mydict.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.activities.ExportDictActivity;
import com.leeeeo.mydict.activities.ImportDictActivity;
import com.leeeeo.mydict.apps.AppEngine;
import com.leeeeo.mydict.models.EasyDictWordsManager;
import com.leeeeo.mydict.utils.WinToast;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class SettingFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final static String TAG = SettingFragment.class.getSimpleName();

    private View mainView = null;
    private TextView tvImport, tvExport, tvCleanLearning, tvCleanVerifying, tvSettingCurrentLib;


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
        mainView = inflater.inflate(R.layout.fragment_setting, container, false);
        initSubViews();
        return mainView;

    }

    private void initSubViews() {
        tvImport = (TextView) mainView.findViewById(R.id.tv_setting_lib_import);
        tvExport = (TextView) mainView.findViewById(R.id.tv_setting_lib_export);
        tvCleanLearning = (TextView) mainView.findViewById(R.id.tv_setting_clean_learn);
        tvCleanVerifying = (TextView) mainView.findViewById(R.id.tv_setting_clean_verify);
        tvSettingCurrentLib = (TextView) mainView.findViewById(R.id.tv_setting_setcurrentlib);


        tvImport.setOnClickListener(this);
        tvExport.setOnClickListener(this);
        tvCleanVerifying.setOnClickListener(this);
        tvCleanLearning.setOnClickListener(this);
        tvSettingCurrentLib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting_clean_learn:
                EasyDictWordsManager.getInstance().clear();
                WinToast.toast(AppEngine.getInstance().getCurrentContext(), "清除学习历史成功");
                break;
            case R.id.tv_setting_clean_verify:
                //WhereCondition whereCondition = EasyDictWordsDao.Properties.
                EasyDictWordsManager.getInstance().clear();
                WinToast.toast(AppEngine.getInstance().getCurrentContext(), "清除测试历史成功");
                break;
            case R.id.tv_setting_lib_export:
                Intent exportIntent = new Intent(getActivity(), ExportDictActivity.class);
                startActivity(exportIntent);
                break;
            case R.id.tv_setting_lib_import:
                Intent importIntent = new Intent(getActivity(), ImportDictActivity.class);
                startActivity(importIntent);
                break;
            case R.id.tv_setting_setcurrentlib:
                showDialog();
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("选择词库").setMultiChoiceItems(new String[]{"四级词汇", "六级词汇", "考研词汇"}, null, null).setPositiveButton("确定", null).setNegativeButton("取消", null).show();
    }
}
