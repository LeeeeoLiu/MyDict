package com.leeeeo.mydict;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends AppCompatActivity {
    private MainUI mainUI;
    private LeftMenu leftMenu;
    private Middle middle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String DB_PATH = "/data/data/com.leeeeo.mydict/databases/";
        String DB_NAME = "question.db";

        if(!(new File(DB_PATH + DB_NAME).exists()))
        {
            File dir = new File(DB_PATH);
            if (!dir.exists())
            {
                dir.mkdir();
            }

            try {
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
                byte[] buffer = new byte[1024];
                int length;

                while((length = is.read(buffer)) > 0)
                {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //		Db db = new Db(this);
//		SQLiteDatabase dbWrite = db.getWritableDatabase();
//
//		ContentValues cv = new ContentValues();
//		cv.put("name", "小张");
//		cv.put("sex", "男");
//		dbWrite.insert("user", null, cv);
//
//		cv = new ContentValues();
//		cv.put("name", "小李");
//		cv.put("sex", "女");
//		dbWrite.insert("user", null, cv);
//
//		dbWrite.close();

//		SQLiteDatabase dbRead = db.getReadableDatabase();
//		Cursor c = dbRead.query("user", null, null, null, null, null, null);
//
//		while(c.moveToNext()){
//			String name = c.getString(c.getColumnIndex("name"));
//			String sex = c.getString(c.getColumnIndex("sex"));
//			System.out.println(String.format("name=%s,sex=%s", name,sex));
//		}

        mainUI=new MainUI(this);
        setContentView(mainUI);
        leftMenu=new LeftMenu();
        middle=new Middle();
        getSupportFragmentManager().beginTransaction().add(MainUI.LEFT_ID, leftMenu).commit();
        getSupportFragmentManager().beginTransaction().add(MainUI.MIDEELE_ID,middle).commit();
//        setContentView(R.layout.activity_main);
    }


}

