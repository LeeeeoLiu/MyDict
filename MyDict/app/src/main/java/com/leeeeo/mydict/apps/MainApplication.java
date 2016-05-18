package com.leeeeo.mydict.apps;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.leeeeo.mydict.models.DaoMaster;

/**
 * Created by Jacob on 16/5/16.
 * Email: Jacob.Deng@about-bob.com
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppEngine.getInstance().init(this);
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "easy_dict.db", null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        AppEngine.getInstance().setDaoSession(new DaoMaster(db).newSession());
    }
}
