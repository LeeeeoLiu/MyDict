package com.leeeeo.mydict.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.apps.BaseActivity;

/**
 * Created by Jacob on 16/5/16.
 * Email: Jacob.Deng@about-bob.com
 */
public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainTabActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}
