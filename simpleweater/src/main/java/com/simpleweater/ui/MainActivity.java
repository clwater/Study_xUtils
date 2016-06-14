package com.simpleweater.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.simpleweater.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static boolean pd = true;
    public static boolean pd2 = false;
    @ViewInject(R.id.main_info)
    private TextView mian_info;

    public static String city = "nochoose";
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        activity = this;

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setSubtitle("副标题");
        actionBar.setTitle("主标题");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);


        pd = checklocation();

        if (pd){
            startActivity(new Intent(this , ChooseActivityOfP.class));
            pd = false;
        }else{
            mian_info.setText(city);
        }




    }

    private boolean checklocation() {

        SharedPreferences sp = activity.getSharedPreferences("SW", MODE_PRIVATE);
        city =  sp.getString("location", "nochoose");

        if(city.equals("nochoose")){
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pd2) {
            mian_info.setText(city);
            SharedPreferences sp = activity.getSharedPreferences("SW", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("location", city);
            editor.commit();
        }
    }
}
