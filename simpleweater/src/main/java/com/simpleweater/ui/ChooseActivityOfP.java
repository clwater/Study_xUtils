package com.simpleweater.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import org.xutils.view.annotation.Event;
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
    public static ProgressDialog pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        baseapp = new BaseApplication();

        setactionbar();



        mRequestQueue =  Volley.newRequestQueue(this);

        if(checkdate()) {
            pr = ProgressDialog.show(this, null, "正在更新相关目录");
            getP();
        }



    }

    @Event(value = R.id.caofp_list,
            type = AdapterView.OnItemClickListener.class
    )
    private void OnTouchListview_caofp(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this , "" + province.get(position).getName() + "  " + province.get(position).getProvince_id() , Toast.LENGTH_SHORT).show();
        Intent next = new Intent(this , ChooseActivityOfC.class);
        next.putExtra("province" , province.get(position).getProvince_id());
        startActivity(next);
    }

    private boolean checkdate() {
        DbManager db = x.getDb(baseapp.getDaoConfig());


        try {
            int all  = (int) db.selector(Table_Province.class).count();
            List<Table_Province> list_pr=db.selector(Table_Province.class).findAll();
            //Log.d("=-=" , "check :  " + all);

            if(all < 1){
                return true;
            }

            listviewtitle = new String[list_pr.size()];
            for (int i=0;i<list_pr.size();i++){
                Log.i("=-=",i+".name="+list_pr.get(i).getName());
                listviewtitle[i] = list_pr.get(i).getName();

                Province pr = new Province();
                pr.setName(list_pr.get(i).getName());
                pr.setProvince_id(list_pr.get(i).getId());
                province.add(pr);
            }
            createlistview();

            return false;
        } catch (DbException e) {
            e.printStackTrace();
        }

        return true;

    }

    private void setactionbar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setSubtitle("副标题");
        actionBar.setTitle("选择省份");
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
                        pr.dismiss();
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




        listviewtitle = new String[province.size()];
        for (int i = 0 ; i < province.size() ; i++){
            listviewtitle[i] = province.get(i).getName();
            Table_Province pr=new Table_Province();
            pr.setName(province.get(i).getName());
            pr.setId(province.get(i).getProvince_id());
            try {
                db.save(pr);
            } catch (DbException e) {}
        }

        createlistview();
    }

    private void createlistview() {


        caofp.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listviewtitle));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_caofp, menu);//获取menu目录下simple.xml的菜单文件
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.update:
                Toast.makeText(this , "您点击了刷新菜单" , Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
