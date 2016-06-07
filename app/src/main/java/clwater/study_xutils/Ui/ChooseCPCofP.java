package clwater.study_xutils.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import clwater.study_xutils.R;

/**
 * Created by yszsyf on 16/6/7.
 */


@ContentView(R.layout.choosecpcofp)
public class ChooseCPCofP  extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        x.view().inject(this);
    }
}
