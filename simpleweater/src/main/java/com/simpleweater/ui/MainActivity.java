package com.simpleweater.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private TextView weater_icon;

    @ViewInject(R.id.weater_temp)
    private TextView weater_temp;

    @ViewInject(R.id.weater_wea)
    private TextView weater_wea;

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



        createicon();


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
        we_icon = new int[100];
        we_icon[0] = R.drawable.p0;
        we_icon[1] = R.drawable.p1;
        we_icon[2] = R.drawable.p2;
        we_icon[3] = R.drawable.p3;
        we_icon[4] = R.drawable.p4;
        we_icon[5] = R.drawable.p5;
        we_icon[6] = R.drawable.p6;
        we_icon[7] = R.drawable.p7;
        we_icon[8] = R.drawable.p8;
        we_icon[9] = R.drawable.p9;
        we_icon[10] = R.drawable.p10;
        we_icon[11] = R.drawable.p11;
        we_icon[12] = R.drawable.p12;
        we_icon[13] = R.drawable.p13;
        we_icon[14] = R.drawable.p14;
        we_icon[15] = R.drawable.p15;
        we_icon[16] = R.drawable.p16;
        we_icon[17] = R.drawable.p17;
        we_icon[18] = R.drawable.p18;
        we_icon[19] = R.drawable.p19;
        we_icon[20] = R.drawable.p20;
        we_icon[21] = R.drawable.p21;
        we_icon[22] = R.drawable.p22;
        we_icon[23] = R.drawable.p23;
        we_icon[24] = R.drawable.p24;
        we_icon[25] = R.drawable.p25;
        we_icon[26] = R.drawable.p26;
        we_icon[27] = R.drawable.p27;
        we_icon[28] = R.drawable.p28;
        we_icon[29] = R.drawable.p29;
        we_icon[30] = R.drawable.p30;
        we_icon[31] = R.drawable.p31;
        we_icon[32] = R.drawable.p32;
        we_icon[33] = R.drawable.p33;
        we_icon[34] = R.drawable.p34;
        we_icon[35] = R.drawable.p35;
        we_icon[36] = R.drawable.p36;
        we_icon[37] = R.drawable.p37;
        we_icon[38] = R.drawable.p38;
        //we_icon[39] = R.drawable.p39;
        we_icon[99] = R.drawable.p99;







    }

    private void getNowWeater() throws UnsupportedEncodingException {
        WeatherNowNetwork request = new WeatherNowNetwork(
                city,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {  //收到成功应答后会触发这里

                        weater_now = WeaterNowAnalysis.getnow(result);
                        //weater_icon.setText(weater_now.getCode().toString());
                        weater_icon.setBackground(getResources().getDrawable(we_icon[Integer.valueOf(weater_now.getCode())]));
                        weater_temp.setText(weater_now.getTemperature() + "˚");
                        weater_wea.setText(weater_now.getText());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里
                        Toast.makeText(MainActivity.this , "获取当前信息失败,请重试.", Toast.LENGTH_SHORT).show();
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//获取menu目录下simple.xml的菜单文件
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_update:
                Toast.makeText(this , "正在获取,请稍后" , Toast.LENGTH_SHORT).show();
                try {
                    getNowWeater();
                } catch (UnsupportedEncodingException e) {}
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
