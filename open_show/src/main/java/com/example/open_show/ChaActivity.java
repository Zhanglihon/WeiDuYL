package com.example.open_show;

import adapter.MyjiKangAdapter1;
import adapter.MyjikangAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.bean.JanBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindInformationList1;
import zhang.bw.com.common.core.exception.ApiException;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ChaActivity extends AppCompatActivity {
    @BindView(R2.id.xrecyc1)
    XRecyclerView xrecyc1;
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
        xrecyc1.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                findInformationList1.reqeust(id,false,"10");
            }

            @Override
            public void onLoadMore() {
                findInformationList1.reqeust(id,true,"10");
            }
        });
        myjiKangAdapter1 = new MyjiKangAdapter1(ChaActivity.this);
        xrecyc1.setAdapter(myjiKangAdapter1);
        xrecyc1.setLayoutManager(new LinearLayoutManager(ChaActivity.this,LinearLayoutManager.VERTICAL,false));
        xrecyc1.refresh();
    }
    class Backo implements DataCall<List<JanBean>>{

        @Override
        public void success(List<JanBean> data, Object... args) {
            xrecyc1.loadMoreComplete();
            xrecyc1.refreshComplete();
            int page = findInformationList1.getPage();
            if(page == 1){
                myjiKangAdapter1.clear();
            }
            myjiKangAdapter1.addALL(data);
            myjiKangAdapter1.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
