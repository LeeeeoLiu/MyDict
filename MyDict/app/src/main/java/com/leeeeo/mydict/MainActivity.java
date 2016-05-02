package com.leeeeo.mydict;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.io.*;

public class MainActivity extends AppCompatActivity {
    private MainUI mainUI;
    private LeftMenu leftMenu;
    private Middle middle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=new Intent(this,ListenNetStateService.class);
        startService(intent);

        String DB_PATH = "/data/data/com.leeeeo.mydict/databases/";
        String DB_NAME = "question.db";

        if (!(new File(DB_PATH + DB_NAME).exists())) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mainUI = new MainUI(this);
        setContentView(mainUI);
        leftMenu = new LeftMenu();
        middle = new Middle();
        getSupportFragmentManager().beginTransaction().add(MainUI.LEFT_ID, leftMenu).commit();
        getSupportFragmentManager().beginTransaction().add(MainUI.MIDEELE_ID, middle).commit();

    }

    private void showDialog(Context context, String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setIcon(R.drawable.icon);
        builder.setTitle(title);
        builder.setMessage(content);
//        builder.setPositiveButton("Button1",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        setTitle("点击了对话框上的Button1");
//                    }
//                });
//        builder.setNeutralButton("Button2",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        setTitle("点击了对话框上的Button2");
//                    }
//                });
//        builder.setNegativeButton("Button3",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        setTitle("点击了对话框上的Button3");
//                    }
//                });
        builder.show();
    }
}

