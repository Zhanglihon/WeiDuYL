package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.MyLishiBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindHistoryInquiryRecord;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.open_inquiry.R;
import com.example.open_inquiry.R2;
import com.example.health.adapter.MyLishiAdapter;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_FABIAOPINGLUN2)
public class MyLishiActivity extends AppCompatActivity {
  private   FindHistoryInquiryRecord findHistoryInquiryRecord;
  private LoginBean loginBean;
  @BindView(R2.id.lishi_recyc)
    RecyclerView lishi_recyc;
  @BindView(R2.id.back1)
    ImageView back1;
  private MyLishiAdapter lishiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lishi);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(MyLishiActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        findHistoryInquiryRecord = new FindHistoryInquiryRecord(new Backd());
        findHistoryInquiryRecord.reqeust(loginBean.getId(),loginBean.getSessionId(),"1","15");
        lishiAdapter = new MyLishiAdapter(this);
        lishi_recyc.setAdapter(lishiAdapter);
        lishi_recyc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lishiAdapter.setBackf(new MyLishiAdapter.Backf() {
            @Override
            public void bac(int i, List<MyLishiBean> list) {
                String doctorId = list.get(i).doctorId;
                String recordId = list.get(i).recordId;
                Intent intent = new Intent(MyLishiActivity.this,MyPingActivity.class);
                intent.putExtra("ax",doctorId);
                intent.putExtra("axc",recordId);
                startActivity(intent);
            }
        });
    }
    class Backd implements DataCall<List<MyLishiBean>>{


        @Override
        public void success(List<MyLishiBean> data, Object... args) {

            lishiAdapter.addAll(data);
            lishiAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
