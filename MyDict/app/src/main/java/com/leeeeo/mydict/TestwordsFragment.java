package com.leeeeo.mydict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TestwordsFragment extends Fragment {

    private String value = "";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("=====>", "TestwordsFragment onAttach");
//		Study mainActivity = (Study) activity;
//		value = mainActivity.getFacebookData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("=====>", "TestwordsFragment onCreateView");
        View v = inflater.inflate(R.layout.frg_testwords, container, false);
        v.findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ExamActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "lallalalalalala", Toast.LENGTH_LONG).show();

            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("=====>", "TestwordsFragment onActivityCreated");

//		TextView txtResult = (TextView) this.getView().findViewById(R.id.textView1);
//		txtResult.setText(value)
    }

}
