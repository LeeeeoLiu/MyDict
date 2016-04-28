package com.leeeeo.mydict;

import android.os.Bundle;
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
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    private MainUI mainUI;
    private LeftMenu leftMenu;
    private Middle middle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUI=new MainUI(this);
        setContentView(mainUI);
        leftMenu=new LeftMenu();
        middle=new Middle();
        getSupportFragmentManager().beginTransaction().add(MainUI.LEFT_ID, leftMenu).commit();
        getSupportFragmentManager().beginTransaction().add(MainUI.MIDEELE_ID,middle).commit();
//        setContentView(R.layout.activity_main);
    }


}

