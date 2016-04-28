package com.leeeeo.mydict;

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

public class Middle extends Fragment{

    private RequestQueue mQueue;
    EditText edit = null;
    Button search = null;
    TextView text = null;
    String YouDaoBaseUrl = "http://fanyi.youdao.com/openapi.do";
    String YouDaoKeyFrom = "Androiddictionary";
    String YouDaoKey = "319821787";
    String YouDaoType = "data";
    String YouDaoDoctype = "json";
    String YouDaoVersion = "1.1";
    private TextView eText2;
    String result;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        eText2= (TextView) v.findViewById(R.id.trans_result);
//        init();
        edit = (EditText) v.findViewById(R.id.edit);
        search = (Button) v.findViewById(R.id.search);
        search.setOnClickListener(new searchListener());
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        v.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity().getApplicationContext(), "词库导出", Toast.LENGTH_SHORT).show();
//            }
//        });
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
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(YouDaoUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TAG", response.toString());
                            String mJSON = response.toString();
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
        }
    }
}
