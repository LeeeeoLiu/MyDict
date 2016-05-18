package com.leeeeo.mydict.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.adapters.DictImportAdpater;
import com.leeeeo.mydict.apps.AppEngine;
import com.leeeeo.mydict.apps.BaseActivity;
import com.leeeeo.mydict.models.beans.DictBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportDictActivity extends BaseActivity {
    private TextView topTitle;
    private ImageView backView;
    private ImageView addView;
    private ListView listView;
    DictImportAdpater dictImportAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_dict);
        initSubViews();
        AsyncLoadDictTask asyncLoadDictTask = new AsyncLoadDictTask();
        //asyncLoadDictTask.execute();
    }

    public void initSubViews() {


        topTitle = (TextView) findViewById(R.id.top_menu_title);
        backView = (ImageView) findViewById(R.id.top_menu_back_btn);
        addView = (ImageView) findViewById(R.id.top_menu_right_btn);

        topTitle.setText("词库导入");
        dictImportAdpater = new DictImportAdpater(this, genList());

        listView = (ListView) findViewById(R.id.lv_import_list);
        listView.setAdapter(dictImportAdpater);
        dictImportAdpater.notifyDataSetChanged();

    }

    public List<DictBean> genList() {
        ArrayList<DictBean> ret = new ArrayList<>();
        ret.add(new DictBean("四级词汇", "../cet4.txt"));
        ret.add(new DictBean("六级词汇", "../cet4.txt"));
        ret.add(new DictBean("考研词汇", "../cet4.txt"));

        return ret;
    }

    class AsyncLoadDictTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<DictBean> ret = new ArrayList<>();
            File file = new File(AppEngine.getImportDir());
            if (!file.exists()) {
                file.mkdirs();
            }
            if (file.isDirectory()) {

                File files[] = file.listFiles();
                for (File file1 : files) {
                    ret.add(new DictBean(file1.getName(), file1.getAbsolutePath()));
                }
            }

            DictImportAdpater dictImportAdpater = new DictImportAdpater(AppEngine.getInstance().getCurrentContext(), ret);
            listView.setAdapter(dictImportAdpater);
            dictImportAdpater.notifyDataSetChanged();
        }
    }
}
