package com.example.open_show;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;

import java.util.List;

import adapter.MyjiKangAdapter1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.bean.JanBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindInformationList1;
import zhang.bw.com.common.core.exception.ApiException;

public class ChaActivity extends AppCompatActivity {
    @BindView(R2.id.xrecyc1)
    RecyclerView xrecyc1;
    private FindInformationList1 findInformationList1;
    private MyjiKangAdapter1 myjiKangAdapter1;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha);
        TextView yy = findViewById(R.id.yy);
        TextPaint paint = yy.getPaint();
        paint.setFakeBoldText(true);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("rr");
        findInformationList1 = new FindInformationList1(new Backo());
        findInformationList1.reqeust(id,"1","10");
        myjiKangAdapter1 = new MyjiKangAdapter1(ChaActivity.this);
        xrecyc1.setAdapter(myjiKangAdapter1);
        xrecyc1.setLayoutManager(new LinearLayoutManager(ChaActivity.this,LinearLayoutManager.VERTICAL,false));

    }
    class Backo implements DataCall<List<JanBean>> {

        @Override
        public void success(List<JanBean> data, Object... args) {
            myjiKangAdapter1.addALL(data);
            myjiKangAdapter1.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
