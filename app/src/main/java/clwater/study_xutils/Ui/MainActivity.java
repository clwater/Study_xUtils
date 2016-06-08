package clwater.study_xutils.Ui;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


    public static boolean firstenter = true;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setSubtitle("副标题");
        actionBar.setTitle("主标题");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);


        button.setText("haoyongbu");
        //test.setText("--==--");
        location = PINYIN.getPinYin("大连");
        mRequestQueue =  Volley.newRequestQueue(this);


        if (firstenter == true) {
            firstenter = false;
            startActivity(new Intent(this, ChooseCPCofP.class));
            //this.finish();

        }



    }


    @Event(value = R.id.button)
    private void onTestBaidu1Click(View view){

        startActivity(new Intent(this , ChooseCPCofP.class));

//        StringRequest request = new StringRequest(
//                //"http://182.254.210.18/QueryCPC/QueryAll.php",
//                "https://api.thinkpage.cn/v3/weather/now.json?key=pqmsn1xd41zne0oy&location=" + location + "&language=zh-Hans&unit=c",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String arg0) {  //收到成功应答后会触发这里
//                        Log.d("=-=" , "zhenna-----=====------" );
//                        Log.d("=-=" , "zhenna" + arg0);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里
//                        Log.d("=-=" , "=-=2");
//                    }
//                }
//        );
//        mRequestQueue.add(request);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//获取menu目录下simple.xml的菜单文件
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refresh:
                Toast.makeText(this , "您点击了刷新菜单" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cancle:
                Toast.makeText(this , "您点击了取消菜单" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this , "您点击了设置菜单" , Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
