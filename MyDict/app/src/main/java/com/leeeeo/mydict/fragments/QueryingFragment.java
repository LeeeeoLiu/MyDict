package com.leeeeo.mydict.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leeeeo.mydict.R;
import com.leeeeo.mydict.models.EasyDictWords;
import com.leeeeo.mydict.models.EasyDictWordsManager;
import com.leeeeo.mydict.utils.WinToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class QueryingFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final static String TAG = QueryingFragment.class.getSimpleName();
    private View mainView = null;
    private TextView tv_query = null;
    private TextView edit;
    private String result;
    private RequestQueue mQueue;
    private Button btnAddToNote;
    public String[] dictLibNames = new String[]{"四级词汇", "六级词汇", "考研词汇", "生词本"};
    private String currentLibName = dictLibNames[3];
    private String currentDictExplains = "";

    public QueryingFragment() {
    }


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
        Button searchBtn = (Button) mainView.findViewById(R.id.search);
        searchBtn.setOnClickListener(new searchListener());

        return mainView;


    }

    private void showDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("选择添加到词库").setSingleChoiceItems(dictLibNames, 3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentLibName = dictLibNames[which];
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EasyDictWords easyDictWords = new EasyDictWords();
                easyDictWords.setName_lib(currentLibName);
                easyDictWords.setExplains(currentDictExplains);
                easyDictWords.setName_words(edit.getText().toString().trim());

                try {
                    EasyDictWordsManager.getInstance().create(easyDictWords);
                    WinToast.toast(getActivity(), "单词添加成功!");
                } catch (Exception e) {
                    WinToast.toast(getActivity(),"单词已经添加到词库了!");
                }
            }
        }).setNegativeButton("取消", null).show();
    }

    private void initSubViews() {
        tv_query = (TextView) mainView.findViewById(R.id.query_result);
        btnAddToNote = (Button) mainView.findViewById(R.id.btn_addtobook);
        btnAddToNote.setOnClickListener(this);
        btnAddToNote.setEnabled(false);
//        tv_query.setText(Html.fromHtml("<h1>有道释义:</h1><hr><br/>英[hə'ləʊ]<br/><br/>" +
//                "美[həˈloʊ]<br/><br/>" +
//                "int.  打招呼; 哈喽，喂; 你好，您好; 表示问候;<br/><br/>" +
//                "n. “喂”的招呼声或问候声;<br/><br/>" +
//                "vi. 喊“喂”;<br/><br/>" +
//                "[例句]Hello, Trish.<br/><br/>" +
//                "你好，特里茜。<br/><br/>" +
//                "[其他]  复数：hellos<br/><br/>"
//        ));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addtobook:
                showDialog();
                break;
        }
    }

    private class searchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            edit = (TextView) mainView.findViewById(R.id.edit);
            mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            String youDaoSearchContent = edit.getText().toString().trim();
            String youDaoBaseUrl = "http://fanyi.youdao.com/openapi.do";
            String youDaoKeyFrom = "Androiddictionary";
            String youDaoKey = "319821787";
            String youDaoType = "data";
            String youDaoDoctype = "json";
            String youDaoVersion = "1.1";
            String youDaoUrl = youDaoBaseUrl + "?keyfrom=" + youDaoKeyFrom + "&key=" + youDaoKey + "&type=" + youDaoType + "&doctype="
                    + youDaoDoctype + "&type=" + youDaoType + "&version=" + youDaoVersion + "&q=" + youDaoSearchContent;
            URL url = null;

            result = ("<h1>有道释义:</h1><hr><br/>");
            try {
                url = new URL(youDaoUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            final URL finalUrl = url;
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(youDaoUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String explainStr = "";
                                JSONObject basic = response.getJSONObject("basic");
                                explainStr += ("英") + basic.getString("uk-phonetic") + ("<br/><br/>");
                                explainStr += ("美") + basic.getString("us-phonetic") + ("<br/><br/>");
                                JSONArray explains = basic.getJSONArray("explains");
                                for (int i = 0; i < explains.length(); i++) {
                                    explainStr += explains.getString(i) + (";<br/><br/>");
                                }
                                currentDictExplains = explainStr;
                                tv_query.setText(Html.fromHtml(result + explainStr));
                                btnAddToNote.setEnabled(true);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                tv_query.setText(Html.fromHtml(result) + "没有查到相关词语~~~");
                                btnAddToNote.setEnabled(false);
                            }

                            Log.e("result", result);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG", error.getMessage(), error);
                    btnAddToNote.setEnabled(false);
                }
            });
            mQueue.add(jsonObjectRequest);
        }
    }
}
