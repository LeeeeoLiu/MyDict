package com.leeeeo.mydict;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String WEATHER_LINK = "http://www.weather.com.cn/data/sk/101280101.html";
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

    private MainUI mainUI;
    private LeftMenu leftMenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUI=new MainUI(this);
        setContentView(mainUI);
        leftMenu=new LeftMenu();
        getSupportFragmentManager().beginTransaction().add(MainUI.LEFT_ID, leftMenu).commit();
//        setContentView(R.layout.activity_main);
//        eText2 = (TextView) findViewById(R.id.trans_result);
//        init();
//        mQueue = Volley.newRequestQueue(this);
    }

    private void init() {
//        edit = (EditText) findViewById(R.id.edit);
//        search = (Button) findViewById(R.id.search);
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

