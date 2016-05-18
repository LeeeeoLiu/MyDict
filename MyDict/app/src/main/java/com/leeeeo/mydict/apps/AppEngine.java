package com.leeeeo.mydict.apps;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Jacob on 16/5/18.
 * Email: Jacob.Deng@about-bob.com
 */
public class AppEngine {

    private static AppEngine instance = null;

    private Context aplicationContext;
    private Context currentContext;
    private Object daoSession = null;

    private AppEngine() {

    }

    public Object getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(Object daoSession) {
        this.daoSession = daoSession;
    }

    public static AppEngine getInstance() {
        if (null == instance) {
            instance = new AppEngine();
        }
        return instance;
    }

    public void init(Context context) {
        this.aplicationContext = context;
    }

    public Context getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(Context currentContext) {
        this.currentContext = currentContext;
    }

    public Context getAplicationContext() {
        return aplicationContext;
    }

    public void setAplicationContext(Context aplicationContext) {
        this.aplicationContext = aplicationContext;
    }

    public static String getExternalBaseDir() {
        return Environment.getExternalStorageDirectory() + File.separator + "easydict";
    }

    public static String getImportDir() {
        File file = new File(getExternalBaseDir() + File.separator + "import" + File.separator);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return getExternalBaseDir() + File.separator + "import" + File.separator;
    }

    public static String getExportDir() {
        File file = new File(getExternalBaseDir() + File.separator + "export" + File.separator);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return getExternalBaseDir() + File.separator + "export" + File.separator;
    }


}
