package com.example.modulecommunication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.modulecommunication.R;

public class FrgmentActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "Ekko";

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private String number1;
    private String number2;
    private EditText editText1;
    private EditText editText2;

    private Handler handler;
    private BaseFragment currentFragment;

    private Button transferBtn1;
    private Button transferBtn2;
    private Button transferBtn3;
    private Button transferBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frgment);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        editText1 = (EditText) findViewById(R.id.num1);
        editText2 = (EditText) findViewById(R.id.num2);
        transferBtn1 = (Button) findViewById(R.id.transferBtn1);
        transferBtn2 = (Button) findViewById(R.id.transferBtn2);
        transferBtn3 = (Button) findViewById(R.id.transferBtn3);
        transferBtn4 = (Button) findViewById(R.id.transferBtn4);
        transferBtn1.setOnClickListener(this);
        transferBtn2.setOnClickListener(this);
        transferBtn3.setOnClickListener(this);
        transferBtn4.setOnClickListener(this);
        HideButton();
        transferBtn1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        number1 = editText1.getText().toString();
        number2 = editText2.getText().toString();
        Log.d(TAG, "number1 = " + number1);
        Log.d(TAG, "number1 = " + number2);
        if (!number1.equals("") && !number2.equals("")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (v.getId()) {
                //****Activity传递数据到Fragment
                case R.id.transferBtn1:
                    //1.直接传入参数 setArguments() 传递数据
                    fragment1 = Fragment1.newInstance(number1, number2);
                    addOrShowFragment(fragmentTransaction, fragment1);
                    break;
                case R.id.transferBtn2:
                    //2.使用handler传递数据
                    //在handler发送数据之前，注意需要先开启fragment。
                    //否则handler变量一直为空 此handler变量是fragment调用setHandler()传来的。
                    if (handler == null) {
                        return;
                    }
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("number1", number1);
                    bundle.putString("number2", number2);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    break;
                case R.id.transferBtn3:

                    break;
                case R.id.transferBtn4:

                    break;
            }
        } else {
            Toast.makeText(FrgmentActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    //判定是否要add还是show fragment
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        Log.d(TAG, "currentFragment = " + currentFragment);
        Log.d(TAG, "fragment = " + fragment);
        if (currentFragment == fragment) {
            return;
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_content, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();

        currentFragment = (BaseFragment) fragment;

    }

    //设置handler对象  由fragment调用
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    //初始化 隐藏所有button
    private void HideButton() {
        transferBtn1.setVisibility(View.GONE);
        transferBtn2.setVisibility(View.GONE);
        transferBtn3.setVisibility(View.GONE);
        transferBtn4.setVisibility(View.GONE);
    }

    //底部导航监听
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            HideButton();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.fragment1:
                    //开启fragment1
                    transferBtn1.setVisibility(View.VISIBLE);
                    if (fragment1 == null) {
                        fragment1 = new Fragment1();
                    }
                    addOrShowFragment(fragmentTransaction, fragment1);
                    return true;
                case R.id.fragment2:
                    //开启fragment2
                    transferBtn2.setVisibility(View.VISIBLE);
                    if (fragment2 == null) {
                        fragment2 = new Fragment2();
                    }
                    addOrShowFragment(fragmentTransaction, fragment2);
                    return true;
                case R.id.fragment3:
                    transferBtn3.setVisibility(View.VISIBLE);
                    return true;
                case R.id.fragment4:
                    transferBtn4.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

}
