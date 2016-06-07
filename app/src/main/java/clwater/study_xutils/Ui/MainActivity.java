package clwater.study_xutils.Ui;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import clwater.study_xutils.R;
import clwater.study_xutils.Tools.PINYIN;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.test)
    TextView test;
    @ViewInject(R.id.button)
    Button button;

    RequestQueue mRequestQueue;
    String location;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(true){
            
        }


        ActionBar actionBar = this.getActionBar();

        //actionBar.show();
        actionBar.setSubtitle("mytest");
        actionBar.setTitle("vogella.com");


        x.view().inject(this);



        button.setText("haoyongbu");


        test.setText("--==--");


        location = PINYIN.getPinYin("大连");


        mRequestQueue =  Volley.newRequestQueue(this);


    }


    @Event(value = R.id.button)
    private void onTestBaidu1Click(View view){

        StringRequest request = new StringRequest(
                //"http://182.254.210.18/QueryCPC/QueryAll.php",
                "https://api.thinkpage.cn/v3/weather/now.json?key=pqmsn1xd41zne0oy&location=" + location + "&language=zh-Hans&unit=c",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String arg0) {  //收到成功应答后会触发这里
                        Log.d("=-=" , "zhenna-----=====------" );
                        Log.d("=-=" , "zhenna" + arg0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里
                        Log.d("=-=" , "=-=2");
                    }
                }
        );
        mRequestQueue.add(request);

    }

}
