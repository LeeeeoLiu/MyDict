package com.leeeeo.mydict;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class LeftMenu extends Fragment {
    private FileActivity fileActivity=new FileActivity();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.left, container, false);
        v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "导入本地词库", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent, 1);
                Intent intent = new Intent(getActivity().getApplicationContext(), FileActivity.class);
                startActivity(intent);
            }
        });
        v.findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Notebook.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "生词本", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Study.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "单词学习", Toast.LENGTH_LONG).show();

            }
        });
        return v;
    }
}

