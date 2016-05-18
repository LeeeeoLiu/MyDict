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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leeeeo.mydict.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jacob on 16/5/16.
 * Email:Jacob.Deng@about-bob.com
 */
public class QueryingFragment extends Fragment implements AdapterView.OnItemClickListener {

    private final static String TAG = QueryingFragment.class.getSimpleName();
    private View mainView = null;
    private TextView tv_query = null;
    private TextView edit;
    private String result;
    private RequestQueue mQueue;

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

    private void initSubViews() {
        tv_query = (TextView) mainView.findViewById(R.id.query_result);
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

    private class searchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            edit= (TextView) mainView.findViewById(R.id.edit);
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
                                JSONObject basic = response.getJSONObject("basic");
                                result +=("英") + basic.getString("uk-phonetic") + ("<br/><br/>");
                                result +=("美") + basic.getString("us-phonetic") + ("<br/><br/>");
                                JSONArray explains = basic.getJSONArray("explains");
                                for (int i = 0; i < explains.length(); i++) {
                                    result += explains.getString(i) + (";<br/><br/>");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tv_query.setText(Html.fromHtml(result));
                            Log.e("result",result);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG", error.getMessage(), error);
                }
            });
            mQueue.add(jsonObjectRequest);
        }
    }
}
