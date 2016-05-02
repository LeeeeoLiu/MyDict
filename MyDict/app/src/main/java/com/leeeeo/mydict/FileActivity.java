package com.leeeeo.mydict;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.StringTokenizer;


public class FileActivity extends AppCompatActivity {


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                Toast.makeText(getApplicationContext(), "开始下载", Toast.LENGTH_SHORT).show();
            }
        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button btn_import = (Button) findViewById(R.id.btn_importlocal);
        Button btn_download = (Button) findViewById(R.id.btn_downloadocal);
        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String urlString = "http://www.leeeeo.com/MyDict/local.db";
                String filepath = "/data/data/com.leeeeo.mydict/databases/";
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            downLoad(urlString, FileActivity.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();
            }
        });
    }

    private void downLoad(String path, Context context) throws Exception {

        //文件被下载到 /data/data/com.leeeeo.mydict/file/
        URL url = new URL(path);
        InputStream is = url.openStream();
        String end = path.substring(path.lastIndexOf("."));
        //打开手机对应的输出流,输出到文件中
        OutputStream os = context.openFileOutput("local" + end, Context.MODE_PRIVATE);
        byte[] buffer = new byte[1024];
        int len = 0;
        //从输入六中读取数据,读到缓冲区中
        while ((len = is.read(buffer)) > 0) {
            os.write(buffer, 0, len);
        }
        //关闭输入输出流
        is.close();
        os.close();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String DB_PATH = "/data/data/com.leeeeo.mydict/databases/local.db";
            Toast.makeText(this, uri.getPath(), Toast.LENGTH_LONG).show();
            try {
                fileCopy(uri.getPath(), DB_PATH);
                Toast.makeText(this, "词库导入成功!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean fileCopy(String oldFilePath, String newFilePath) throws IOException {
        //如果原文件不存在
        if (!fileExists(oldFilePath)) {
            return false;
        }
        //获得原文件流
        FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
        byte[] data = new byte[1024];
        //输出流
        FileOutputStream outputStream = new FileOutputStream(new File(newFilePath));
        //开始处理流
        while (inputStream.read(data) != -1) {
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
        return true;
    }

    private static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

}
