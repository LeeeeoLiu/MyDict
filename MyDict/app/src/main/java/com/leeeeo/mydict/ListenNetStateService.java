package com.leeeeo.mydict;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.widget.Toast;

public class ListenNetStateService extends Service {
    private ConnectivityManager connectivityManager;
    private NetworkInfo info;
    private InternetStatus internetStatus=new InternetStatus(0);

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                Toast.makeText(getApplicationContext(), "网络状态已经改变", Toast.LENGTH_SHORT).show();
                connectivityManager = (ConnectivityManager)

                        getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if(info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    Toast.makeText(getApplicationContext(), "当前网络名称：" + name, Toast.LENGTH_SHORT).show();
                    internetStatus.setStatus(1);
                } else {
                    Toast.makeText(getApplicationContext(), "没有可用网络\n系统将开启离线查询,请确保已导入离线词库!", Toast.LENGTH_SHORT).show();
                    internetStatus.setStatus(0);

                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}