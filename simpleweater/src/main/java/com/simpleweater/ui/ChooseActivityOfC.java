package com.simpleweater.ui;

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
import com.android.volley.toolbox.Volley;
import com.simpleweater.R;
import com.simpleweater.base.BaseApplication;
import com.simpleweater.tools.analysis.CityAnalysis;
import com.simpleweater.tools.dbmodel.Table_City;
import com.simpleweater.tools.model.City;
import com.simpleweater.tools.model.Province;
import com.simpleweater.tools.network.CityNetwork;
import com.simpleweater.tools.network.ProvinceNetwork;

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
@ContentView(R.layout.chooseactivityofc)
public class ChooseActivityOfC extends AppCompatActivity {

    @ViewInject(R.id.caofc_list)
    private ListView caofc;


    private String[] listviewtitle;
    private RequestQueue mRequestQueue;
    private Vector<City> city = new Vector<City>();
    private BaseApplication baseapp;
    public static ProgressDialog pr;
    private String parentP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        baseapp = new BaseApplication();

        setactionbar();

        Intent getintent = getIntent();
        parentP = getintent.getStringExtra("province");



        mRequestQueue =  Volley.newRequestQueue(this);

        if(checkdate()) {
            pr = ProgressDialog.show(this, null, "正在更新相关目录");
            getC();
        }



    }

    @Event(value = R.id.caofc_list,
            type = AdapterView.OnItemClickListener.class
    )
    private void OnTouchListview_caofp(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this , "" + city.get(position).getName() , Toast.LENGTH_SHORT).show();
        MainActivity.pd2 = true;
        MainActivity.city = city.get(position).getName();
        Intent next = new Intent(this , MainActivity.class);
        startActivity(next);
    }

    private boolean checkdate() {

        DbManager db = x.getDb(baseapp.getDaoConfig());

        try {


            List<Table_City> list_pr = db.selector(Table_City.class).where("parent", "=", parentP).findAll();
            int all  = (int) db.selector(Table_City.class).where("parent", "=", parentP).count();
            //List<Table_City> list_pr=db.selector(Table_City.class).findAll();

            if(all < 1){
                return true;
            }
            listviewtitle = new String[list_pr.size()];
            for (int i=0;i<list_pr.size();i++){
               // Log.i("=-=2",i+".name="+list_pr.get(i).getName());
                listviewtitle[i] = list_pr.get(i).getName();
                City c = new City();
                c.setName(list_pr.get(i).getName());
                city.add(c);

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
        actionBar.setTitle("选择城市");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.test);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    private void getC() {
        CityNetwork request = new CityNetwork(
                parentP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {  //收到成功应答后会触发这里
                        city = CityAnalysis.cityanalysis(result);
                        setlisttitle();
                        pr.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) { //出现连接错误会触发这里
                        Toast.makeText(ChooseActivityOfC.this , "获取城市列表失败,请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        mRequestQueue.add(request);

    }

    private void setlisttitle() {
        DbManager db = x.getDb(baseapp.getDaoConfig());
        listviewtitle = new String[city.size()];
        for (int i = 0 ; i < city.size() ; i++){
            listviewtitle[i] = city.get(i).getName();
            Table_City pr=new Table_City();
            pr.setName(city.get(i).getName());
            pr.setParent(parentP);
            //pr.setId(city.get(i).getProvince_id());
            try {
                db.save(pr);
            } catch (DbException e) {}
        }

        createlistview();
    }

    private void createlistview() {


        caofc.setAdapter(new ArrayAdapter<String>(this,
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
