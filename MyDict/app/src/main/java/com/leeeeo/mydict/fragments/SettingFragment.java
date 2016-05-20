package com.leeeeo.mydict.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leeeeo.mydict.R;
import com.leeeeo.mydict.apps.AppEngine;
import com.leeeeo.mydict.models.EasyDictWords;
import com.leeeeo.mydict.models.EasyDictWordsDao;
import com.leeeeo.mydict.models.EasyDictWordsManager;
import com.leeeeo.mydict.utils.WinToast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class SettingFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final static String TAG = SettingFragment.class.getSimpleName();

    private View mainView = null;
    private TextView tvImport, tvExport, tvCleanLearning, tvCleanVerifying, tvSettingCurrentLib, tvGlobalLibName;
    private String currentLibName = AppEngine.dictLibNames[0];

    private String currentExportName = AppEngine.dictLibNames[0];
    private String currentImportName = AppEngine.dictLibNames[0];

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
        tvGlobalLibName = (TextView) mainView.findViewById(R.id.tv_global_lib_name);
        tvGlobalLibName.setText(AppEngine.getInstance().getGlobalLibName());

        tvImport.setOnClickListener(this);
        tvExport.setOnClickListener(this);
        tvCleanVerifying.setOnClickListener(this);
        tvCleanLearning.setOnClickListener(this);
        tvSettingCurrentLib.setOnClickListener(this);

        TextView textView = (TextView) mainView.findViewById(R.id.tv_setting_version);
        textView.setText("EasyDict(Qinghe Tan) " + AppEngine.getInstance().getAppInfo());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting_clean_learn:
                new AlertDialog.Builder(getActivity()).setTitle("确定清空").setMessage("清空后,学习数据都没有啰?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WhereCondition whereCondition = EasyDictWordsDao.Properties.Islearn.eq(true);
                        List<EasyDictWords> tmpList = EasyDictWordsManager.getInstance().list(whereCondition);

                        Iterator<EasyDictWords> iterator = tmpList.iterator();
                        while (iterator.hasNext()) {
                            EasyDictWords easyDictWords = iterator.next();
                            easyDictWords.setIslearn(false);
                        }

                        EasyDictWordsManager.getInstance().updateForce(tmpList);
                        WinToast.toast(AppEngine.getInstance().getCurrentContext(), "清除学习历史成功");
                    }
                }).setNegativeButton("取消", null).show();

                break;
            case R.id.tv_setting_clean_verify:
                //WhereCondition whereCondition = EasyDictWordsDao.Properties.
                new AlertDialog.Builder(getActivity()).setTitle("确定清空").setMessage("清空后,测试数据都没有啰?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WhereCondition whereCondition = EasyDictWordsDao.Properties.Islearn.eq(true);
                        List<EasyDictWords> tmpList = EasyDictWordsManager.getInstance().list(whereCondition);

                        Iterator<EasyDictWords> iterator = tmpList.iterator();
                        while (iterator.hasNext()) {
                            EasyDictWords easyDictWords = iterator.next();
                            easyDictWords.setIslearn(false);
                        }

                        EasyDictWordsManager.getInstance().updateForce(tmpList);
                        WinToast.toast(AppEngine.getInstance().getCurrentContext(), "清除测试历史成功");
                    }
                }).setNegativeButton("取消", null).show();

                break;
            case R.id.tv_setting_lib_export:
                showExportDialog();
                break;
            case R.id.tv_setting_lib_import:
                showImportDialog();
                break;
            case R.id.tv_setting_setcurrentlib:
                showDialog();
                break;
            default:
                break;
        }
    }

    private void showImportDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("选择全局词库").setSingleChoiceItems(AppEngine.dictLibNames, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentImportName = AppEngine.dictLibNames[which];
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //WhereCondition whereCondition = EasyDictWordsDao.Properties.Name_lib.eq(currentImportName);
                //List<EasyDictWords> tmp_list = EasyDictWordsManager.getInstance().list(whereCondition);
                new ImportAsyncTask().execute(currentImportName, null, null);

            }
        }).setNegativeButton("取消", null).show();
    }

    private void showExportDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("选择全局词库").setSingleChoiceItems(AppEngine.dictLibNames, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentExportName = AppEngine.dictLibNames[which];
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WhereCondition whereCondition = EasyDictWordsDao.Properties.Name_lib.eq(currentExportName);
                List<EasyDictWords> tmp_list = EasyDictWordsManager.getInstance().list(whereCondition);
                new ExportAsyncTask().execute(tmp_list, null, null);

            }
        }).setNegativeButton("取消", null).show();
    }

    private void showDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("选择全局词库").setSingleChoiceItems(AppEngine.dictLibNames, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentLibName = AppEngine.dictLibNames[which];
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WinToast.toast(getActivity(), "设置成功!");
                AppEngine.getInstance().setGlobalLibName(currentLibName);
                tvGlobalLibName.setText(currentLibName);
            }
        }).setNegativeButton("取消", null).show();
    }

    class ImportAsyncTask extends AsyncTask<String, Integer, Exception> {

        @Override
        protected Exception doInBackground(String... params) {
            File file = new File(AppEngine.getImportDir() + File.separator + currentImportName + ".txt");
            if (!file.exists()) {
                return new FileNotFoundException(file.getAbsolutePath());
            }

            try {
                String ret = null;
                FileInputStream inputFile = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputFile.read(buffer);
                inputFile.close();
                ret = new String(buffer);

                List<EasyDictWords> list = new Gson().fromJson(ret, new TypeToken<List<EasyDictWords>>() {
                }.getType());
                if (null == list) {
                    return new FileNotFoundException("文件内容是空");
                }

                WhereCondition whereCondition = EasyDictWordsDao.Properties.Name_lib.eq(currentImportName);
                EasyDictWordsManager.getInstance().clear(whereCondition);

                EasyDictWordsManager.getInstance().create(list);

                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return e;
            }
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            if (null == s) {
                WinToast.toast(getActivity(), "导入成功!");
            } else {
                WinToast.toast(getActivity(), "导入出错:" + s.getMessage());
            }
        }
    }

    class ExportAsyncTask extends AsyncTask<List<EasyDictWords>, Integer, String> {
        @Override
        protected String doInBackground(List<EasyDictWords>... params) {
            String jsonString = new Gson().toJson(params[0]);

            File file = new File(AppEngine.getExportDir() + File.separator + currentExportName + ".txt");
            if (!new File(file.getParent()).exists()) {
                new File(file.getParent()).mkdirs();
            }
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(jsonString.getBytes());
                outputStream.close();
                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (TextUtils.isEmpty(s)) {
                WinToast.toast(getActivity(), "导出失败:");
            }

            if (s.equalsIgnoreCase("ok")) {
                WinToast.toast(getActivity(), "导出成功");
            } else {
                WinToast.toast(getActivity(), "导出失败:" + s);
            }
        }

        //


    }
}
