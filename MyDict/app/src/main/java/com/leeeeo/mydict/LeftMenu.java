package com.leeeeo.mydict;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class LeftMenu extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.left, container,false);
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("Hello leeeeo.com");
                Toast toast=Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
			}
		});
		return v;
	}
}
