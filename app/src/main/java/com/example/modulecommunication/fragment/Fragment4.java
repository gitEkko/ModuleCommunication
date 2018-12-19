package com.example.modulecommunication.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.modulecommunication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends BaseFragment {


    @Override
    public void onResume() {
        super.onResume();
        //注册EventBus,注意参数是this，传入activity会报错
        EventBus.getDefault().register(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button returnBtn1 = (Button) view.findViewById(R.id.return_Btn);
        final TextView result = (TextView) view.findViewById(R.id.fragment_result);
        //点击按钮，将数据从fragment传给activity
        returnBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = result.getText().toString();
                //EventBus发送数据
                EventBus.getDefault().post(data);
            }
        });
    }

    //EventBus的处理事件函数  该方法可以随意取名 必须为public 必须添加注解并指定线程模型
    //之后EventBus会自动扫描到此函数，进行数据传递
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(Bundle bundle) {
        String number1 = bundle.getString(activity.SaveData1);
        String number2 = bundle.getString(activity.SaveData2);

        //进行数据操作
        textResult.setText(doAddition(number1, number2));
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_fragment4;
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消EventBus注册
        EventBus.getDefault().unregister(this);
    }
}