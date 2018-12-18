package com.example.modulecommunication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.modulecommunication.R;


public abstract class BaseFragment extends Fragment {

    private final String TAG = "Ekko - " + this.getClass().getSimpleName();
    public View rootView;
    FrgmentActivity activity;

    //public TextView textViewNum1;
    //public TextView textViewNum2;
    public TextView textResult;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取activity实例  所有子fragment可用
        activity = (FrgmentActivity) context;
        Log.e(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        rootView = inflater.inflate(setFragmentLayoutID(), container, false);
        //textViewNum1 = (TextView) rootView.findViewById(R.id.fragment_num1);
        // textViewNum2 = (TextView) rootView.findViewById(R.id.fragment_num2);
        textResult = (TextView) rootView.findViewById(R.id.fragment_result);
        return rootView;
    }

    public String doAddition(String num1, String num2) {
        int number1 = Integer.parseInt(num1);
        int number2 = Integer.parseInt(num2);
        int result = number1 + number2;
        return String.valueOf(result);
    }

    public abstract int setFragmentLayoutID();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, "onHiddenChanged : hidden = " + hidden);
    }
}
