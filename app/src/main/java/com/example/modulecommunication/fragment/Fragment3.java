package com.example.modulecommunication.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.example.modulecommunication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends BaseFragment {

    private MyReceiver myReceiver;

    @Override
    public void onResume() {
        super.onResume();
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(activity.ACTION_NAME);
        //注册广播
        //activity.registerReceiver(myReceiver, intentFilter);
        LocalBroadcastManager.getInstance(activity).registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_fragment3;
    }


    //创建广播接收实例
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取数据
            String number1 = intent.getStringExtra(activity.SaveData1);
            String number2 = intent.getStringExtra(activity.SaveData2);
            //进行数据操作
            textResult.setText(doAddition(number1, number2));
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //取消注册广播
        //activity.unregisterReceiver(myReceiver);
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(myReceiver);
    }
}