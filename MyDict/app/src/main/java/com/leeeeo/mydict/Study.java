package com.leeeeo.mydict;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

//
//public class Study extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.study, container, false);
////        v.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(getActivity().getApplicationContext(), "词库导入", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        return v;
//    }
//}
public class Study extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.study);

        FragmentTabHost tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //1
        tabHost.addTab(tabHost.newTabSpec("学习新单词")
                        .setIndicator("学习新单词"),
                LearnwordsFragment.class,
                null);
        //2
        tabHost.addTab(tabHost.newTabSpec("浏览生词本")
                        .setIndicator("浏览生词本"),
                ReviewwordsFragment.class,
                null);
        //3
        tabHost.addTab(tabHost.newTabSpec("测试已学单词")
                        .setIndicator("测试已学单词"),
                TestwordsFragment.class,
                null);
        //4
        tabHost.addTab(tabHost.newTabSpec("待开发...")
                        .setIndicator("待开发..."),
                OthersFragment.class,
                null);
    }

    /**************************
     *
     * 給子頁籤呼叫用
     *
     **************************/
//    public String getAppleData() {
//        return "Apple 123";
//    }
//
//    public String getGoogleData() {
//        return "Google 456";
//    }
//
//    public String getFacebookData() {
//        return "Facebook 789";
//    }
//
//    public String getTwitterData() {
//        return "Twitter abc";
//    }
}