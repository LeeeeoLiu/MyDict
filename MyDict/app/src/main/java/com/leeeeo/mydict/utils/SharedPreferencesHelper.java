package com.leeeeo.mydict.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacob on 2015/12/11.
 * Email:Jacob.Deng@about-bob.com
 * <p/>
 * Cache 策略:
 * Cache 的构建:
 * 1. 第一次查询,没有从sp 里面获取,存到map里面
 * 2. 非第一次查询,都重map 里面获取内容
 * 3. 写,先写内存,再设置写标志,定期根据写标志写入sp里面.
 */
public class SharedPreferencesHelper {

    private final static String TAG = SharedPreferencesHelper.class.getSimpleName();

    public static SharedPreferencesHelper spHelper;

    public static SharedPreferences preferences;

    public static Editor editor;

    final public static boolean enableCache = true;


    //final private static HashMap<String, Object> spCache = new HashMap<>();

    final private static Set<String> needWriteKeys = Collections.synchronizedSet(new HashSet<String>());

    public static SharedPreferencesHelper getInstance(Context mContext) {
        if (spHelper == null) {
            spHelper = new SharedPreferencesHelper();
            preferences = mContext.getSharedPreferences("Preferences",
                    Activity.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return spHelper;
    }

    public void putObject(String key, Object object) {
        _putObject(key, object);

    }

    public void _putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            String strList = new String(Base64.encode(baos.toByteArray(), Base64.NO_WRAP));
            putString(key, strList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {

        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Object getObject(String key) {
        return _getObject(key);
    }

    public Object _getObject(String key) {
        String message = getString(key);
        Object ret = null;
        byte[] buffer = Base64.decode(message.getBytes(), Base64.NO_WRAP);
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            ret = ois.readObject();
            ois.close();
            return ret;
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return ret;
    }

    public void putString(String key, String value) {
        _putString(key, value);
    }

    public void _putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();

    }

    public String getString(String key) {
        return _getString(key);

    }

    public String _getString(String key) {
        return preferences.getString(key, "");
    }

    public void putInt(String key, int value) {
        _putInt(key, value);

    }

    public void _putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int flag) {
        return preferences.getInt(key, flag);
    }

    public void putBoolean(String key, boolean value) {
        _putBoolean(key, value);

    }

    public void _putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean flag) {
        return _getBoolean(key, flag);

    }

    public boolean _getBoolean(String key, boolean flag) {

        return preferences.getBoolean(key, flag);
    }

    public void clear() {
        editor.clear().commit();
    }

    public void removeString(String key) {
        editor.remove(key);
        editor.commit();
    }
}
