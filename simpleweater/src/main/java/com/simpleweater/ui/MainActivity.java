package com.simpleweater.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;
import com.simpleweater.R;
import com.simpleweater.tools.analysis.ProvinceAnalysis;
import com.simpleweater.tools.analysis.WeaterNowAnalysis;
import com.simpleweater.tools.dbmodel.Weater_now;
import com.simpleweater.tools.network.ProvinceNetwork;
import com.simpleweater.tools.network.WeatherNowNetwork;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static boolean pd = true;
    public static boolean pd2 = false;
    @ViewInject(R.id.main_info)
    private TextView mian_info;

    @ViewInject(R.id.weater_icon)
    private ImageView weater_icon;

    @ViewInject(R.id.weater_temp)
    private TextView weater_temp;

    public static String city = "nochoose";
    private Activity activity;
    private RequestQueue mRequestQueue;
    private Weater_now weater_now;
    private int[] we_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        activity = this;
        mRequestQueue =  Volley.newRequestQueue(this);


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
            createicon();
           // mian_info.setText(city);
            //String icon_path = "R.drawable." + weater_now.getCode();
           // weater_icon.setImageDrawable(getResources().getDrawable(icon_path);



            try {
                getNowWeater();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }




        }





    }

    private void createicon() {
        //we_icon[0] = R.drawable.icon0;
    }

    private void getNowWeater() throws UnsupportedEncodingException {
        WeatherNowNetwork request = new WeatherNowNetwork(
                city,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {  //收到成功应答后会触发这里
                        Logger.d(result);
                        Logger.json(result);
                        //weater_now = WeaterNowAnalysis.getnow(result);
                        //weater_temp.setText(weater_now.getTemperature() + "˚");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里
                        //Toast.makeText(ChooseActivityOfP.this , "获取城市列表失败,请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        mRequestQueue.add(request);
    }

    private boolean checklocation() {

        SharedPreferences sp = activity.getSharedPreferences("SW", MODE_PRIVATE);
        city =  sp.getString("location", "nochoose");

        //Logger.d(city);

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
