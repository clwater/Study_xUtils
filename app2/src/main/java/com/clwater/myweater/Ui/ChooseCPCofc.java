package com.clwater.myweater.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.clwater.myweater.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;




/**
 * Created by yszsyf on 16/6/7.
 */


@ContentView(R.layout.choosecpcofc)
public class ChooseCPCofc extends AppCompatActivity {


    @ViewInject(R.id.ccpcpc)
    ListView ccpcpp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("选择地址");
        actionBar.setSubtitle("选择地级市");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);





    }



}
