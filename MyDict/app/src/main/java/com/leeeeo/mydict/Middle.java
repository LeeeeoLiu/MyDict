package com.leeeeo.mydict;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Middle extends Fragment {

    private RequestQueue mQueue;
    EditText edit = null;
    Button search = null;
    Button btn_addtobook = null;
    TextView text = null;
    String YouDaoBaseUrl = "http://fanyi.youdao.com/openapi.do";
    String YouDaoKeyFrom = "Androiddictionary";
    String YouDaoKey = "319821787";
    String YouDaoType = "data";
    String YouDaoDoctype = "json";
    String YouDaoVersion = "1.1";
    String Trans;
    private TextView eText2;
    String result;
    private Db db;
    private SQLiteDatabase dbRead, dbWrite;
    private InternetStatus internetStatus;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        eText2 = (TextView) v.findViewById(R.id.trans_result);
        edit = (EditText) v.findViewById(R.id.edit);
        search = (Button) v.findViewById(R.id.search);
        btn_addtobook = (Button) v.findViewById(R.id.btn_addtobook);
        search.setOnClickListener(new searchListener());
        v.findViewById(R.id.btn_addtobook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new Db(getActivity().getApplicationContext());
                dbWrite = db.getWritableDatabase();
                ContentValues cValue = new ContentValues();
                cValue.put("word", edit.getText().toString().trim());
                cValue.put("trans", Trans);
                cValue.put("explain", result);
                //调用insert()方法插入数据
                dbWrite.insert("vocab", null, cValue);
                dbWrite.close();
                Toast.makeText(getActivity().getApplicationContext(), "成功添加到生词本!", Toast.LENGTH_LONG).show();
                btn_addtobook.setEnabled(false);
            }
        });
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        return v;
    }

    private void init() {
        edit = (EditText) getView().findViewById(R.id.edit);
        search = (Button) getView().findViewById(R.id.search);

        search.setOnClickListener(new searchListener());
    }

    private class searchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if ("".equals(edit.getText().toString().trim())) {
                Toast.makeText(getActivity().getApplicationContext(), "请输入单词后再点击", Toast.LENGTH_SHORT).show();

            } else {
                btn_addtobook = (Button) getView().findViewById(R.id.btn_addtobook);
                btn_addtobook.setEnabled(true);
            }
            int status = internetStatus.getStatus();
            if (status == 1) {
                String YouDaoSearchContent = edit.getText().toString().trim();
                String YouDaoUrl = YouDaoBaseUrl + "?keyfrom=" + YouDaoKeyFrom + "&key=" + YouDaoKey + "&type=" + YouDaoType + "&doctype="
                        + YouDaoDoctype + "&type=" + YouDaoType + "&version=" + YouDaoVersion + "&q=" + YouDaoSearchContent;
                URL url = null;

                result = YouDaoSearchContent + (":\n");
                try {
                    url = new URL(YouDaoUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                final URL finalUrl = url;
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(YouDaoUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("TAG", response.toString());
                                String mJSON = response.toString();

                                try {
                                    Trans = response.getString("translation");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.print(finalUrl.toString());
                                try {
                                    result += ("基本释义:\n");
                                    JSONObject basic = response.getJSONObject("basic");
                                    JSONArray explains = basic.getJSONArray("explains");
                                    for (int i = 0; i < explains.length(); i++) {
                                        result += explains.getString(i) + ("\n");
                                    }
                                    result += ("网络释义:");
                                    JSONArray web = response.getJSONArray("web");
                                    for (int i = 0; i < web.length(); i++) {
                                        JSONObject w = web.getJSONObject(i);
                                        result += ("\n") + w.getString("key");
                                        result += ("\n") + w.getString("value");
                                        result += ("\n-----------------------");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                eText2.setText(result);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });
                mQueue.add(jsonObjectRequest);
            }else
            {

            }
        }
    }
}
