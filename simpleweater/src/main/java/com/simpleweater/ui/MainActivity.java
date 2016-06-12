package com.simpleweater.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simpleweater.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static boolean pd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setSubtitle("副标题");
        actionBar.setTitle("主标题");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);

        if (pd){
            startActivity(new Intent(this , ChooseActivityOfP.class));
            pd = false;
        }



    }
}
