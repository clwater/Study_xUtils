package clwater.study_xutils;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import org.xutils.common.util.LogUtil;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.test)
    TextView test;
    @ViewInject(R.id.button)
    Button button;

    RequestQueue mRequestQueue;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        x.view().inject(this);



        button.setText("haoyongbu");


        test.setText("--==--");



        mRequestQueue =  Volley.newRequestQueue(this);


    }


    @Event(value = R.id.button)
    private void onTestBaidu1Click(View view){

        StringRequest request = new StringRequest(
                "https://api.thinkpage.cn/v3/weather/now.json?key=pqmsn1xd41zne0oy&location=%E5%A4%A7%E8%BF%9E&language=zh-Hans&unit=c",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String arg0) {  //收到成功应答后会触发这里
                        Log.d("=-=" , "zhenna" + arg0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里

                    }
                }
        );
        mRequestQueue.add(request);

    }

}
