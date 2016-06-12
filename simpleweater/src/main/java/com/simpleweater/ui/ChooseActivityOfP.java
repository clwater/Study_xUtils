package com.simpleweater.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.simpleweater.R;
import com.simpleweater.base.BaseApplication;
import com.simpleweater.tools.analysis.ProvinceAnalysis;
import com.simpleweater.tools.dbmodel.Table_Province;
import com.simpleweater.tools.model.Province;
import com.simpleweater.tools.network.ProvinceNetwork;

import org.json.JSONException;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;
import java.util.Vector;

/**
 * Created by yszsyf on 16/6/12.
 */
@ContentView(R.layout.chooseactivityofp)
public class ChooseActivityOfP extends AppCompatActivity {

    @ViewInject(R.id.caofp_list)
    private ListView caofp;


    private String[] listviewtitle;
    private RequestQueue mRequestQueue;
    private Vector<Province> province = new Vector<Province>();
    private BaseApplication baseapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        baseapp = new BaseApplication();

        setactionbar();
        mRequestQueue =  Volley.newRequestQueue(this);

        if(true) {
            getP();
        }
        


    }

    private void setactionbar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setSubtitle("副标题");
        actionBar.setTitle("选择城市");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    private void getP() {
        ProvinceNetwork request = new ProvinceNetwork(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {  //收到成功应答后会触发这里
                        province = ProvinceAnalysis.provinceanalysis(result);
                        setlisttitle();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里
                        Toast.makeText(ChooseActivityOfP.this , "获取城市列表失败,请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        mRequestQueue.add(request);

    }

    private void setlisttitle() {
        DbManager db = x.getDb(baseapp.getDaoConfig());

        Table_Province person1=new Table_Province();
        person1.setName("a");
        person1.setId("1");
        Table_Province person2=new Table_Province();
        person2.setName("b");
        person2.setId("2");
        try {
            db.save(person1);
            db.save(person2);
        } catch (DbException e) {
            e.printStackTrace();
        }

        DbManager db2 = x.getDb(((BaseApplication)getApplicationContext()).getDaoConfig());
        try {
            List<Table_Province> lyjPersons=db2.selector(Table_Province.class).findAll();
            for (int i=0;i<lyjPersons.size();i++){
                Log.i("=-=","LYJPerson"+i+".name="+lyjPersons.get(i).getName());
                Log.i("=-=","LYJPerson"+i+".id="+lyjPersons.get(i).getId());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }






        listviewtitle = new String[province.size()];
        for (int i = 0 ; i < province.size() ; i++){
            listviewtitle[i] = province.get(i).getName();
        }

        createlistview();
    }

    private void createlistview() {


        caofp.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listviewtitle));
    }
}
