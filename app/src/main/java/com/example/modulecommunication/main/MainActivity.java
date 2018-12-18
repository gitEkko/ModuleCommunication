package com.example.modulecommunication.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.modulecommunication.R;
import com.example.modulecommunication.fragment.FrgmentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void toFrgmentActivity(View view) {
        jumpActivity(FrgmentActivity.class);
    }

}
