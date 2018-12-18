package com.example.modulecommunication.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.modulecommunication.R;

/**
 * A simple {@link Fragment} subclass.
 * 继承BaseFragment
 */
public class Fragment2 extends BaseFragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //设置Activity的handler对象
        //为的是 activity和fragment使用同一个handler对象 才能进行handler通信
        activity.setHandler(handler);
    }

    //定义handler 用于接收消息 接收activity传递来的数据
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            String number1 = bundle.getString("number1");
            String number2 = bundle.getString("number2");

            //textViewNum1.setText(number1);
            //textViewNum2.setText(number2);
            //进行数据操作
            textResult.setText(doAddition(number1, number2));
        }
    };


    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_fragment2;
    }
}
