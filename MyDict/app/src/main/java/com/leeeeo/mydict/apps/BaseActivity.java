package com.leeeeo.mydict.apps;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jacob on 16/5/18.
 * Email: Jacob.Deng@about-bob.com
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppEngine.getInstance().setCurrentContext(this);
    }
}
