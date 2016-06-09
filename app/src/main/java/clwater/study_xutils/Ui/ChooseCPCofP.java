package clwater.study_xutils.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import clwater.study_xutils.R;

/**
 * Created by yszsyf on 16/6/7.
 */


@ContentView(R.layout.choosecpcofp)
public class ChooseCPCofP  extends AppCompatActivity {


    @ViewInject(R.id.ccpcpp)
    ListView ccpcpp;

    public static boolean firstenter = true;
    RequestQueue mRequestQueue;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("选择地址");
        actionBar.setSubtitle("选择省,直辖市,特别行政区");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);


        if (firstenter == true) {
            firstenter = false;
            startActivity(new Intent(this, ChooseCPCofc.class));
            //this.finish();
        }



    }
}
