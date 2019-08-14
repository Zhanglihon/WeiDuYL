package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.open_inquiry.R;
import com.example.open_inquiry.R2;
import com.example.health.adapter.MyUserEvaluateXingAdapter;
import com.example.health.adapter.MyUserEvaluateXingAdapter1;
import com.example.health.presenter.EvaluationInquiry;

public class MyPingActivity extends AppCompatActivity {
    private EvaluationInquiry evaluationInquiry;
    private LoginBean loginBean;
    @BindView(R2.id.io)
    TextView io;
    @BindView(R2.id.wancheng)
    TextView wangcheng;
    @BindView(R2.id.hui)
    TextView hui;
    @BindView(R2.id.pingedit)
    EditText pingedit;
    private String ax;
    private String axc;
    @BindView(R2.id.zuandu)
    RecyclerView zuandu;
    @BindView(R2.id.zuanfudu)
    RecyclerView zuanfudu;
    private MyUserEvaluateXingAdapter myUserEvaluateXingAdapter;
    private MyUserEvaluateXingAdapter1 myUserEvaluateXingAdapter1;
    private int a1;
    private int a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ping);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(MyPingActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        ax = getIntent().getStringExtra("ax");
        axc = getIntent().getStringExtra("axc");
        myUserEvaluateXingAdapter = new MyUserEvaluateXingAdapter(this);
        zuandu.setAdapter(myUserEvaluateXingAdapter);
        myUserEvaluateXingAdapter1 = new MyUserEvaluateXingAdapter1(this);
        zuanfudu.setAdapter(myUserEvaluateXingAdapter1);
        zuandu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        zuanfudu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myUserEvaluateXingAdapter.setClakBack(new MyUserEvaluateXingAdapter.ClakBack() {
            @Override
            public void getdta(int a) {
                a1 = a;
            }
        });
        myUserEvaluateXingAdapter1.setClakBack(new MyUserEvaluateXingAdapter1.ClakBack() {
            @Override
            public void getdta(int a) {
                a2= a;
            }
        });
        wangcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = pingedit.getText().toString();
                if(s.isEmpty()){
                    Toast.makeText(MyPingActivity.this,"你不能评论空,亲",Toast.LENGTH_LONG).show();
                }else {
                    evaluationInquiry = new EvaluationInquiry(new Backj());
                    evaluationInquiry.reqeust(loginBean.getId(),loginBean.getSessionId(), axc, ax,s,a1+"",a2+"");
                }
            }
        });
        hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    class Backj implements DataCall<String>{

        @Override
        public void success(String data, Object... args) {
            if (data != null) {
                Toast.makeText(MyPingActivity.this, "不能重复评价", Toast.LENGTH_LONG).show();
            } else{
                Intent intent = new Intent(MyPingActivity.this,MyPingActivity1.class);
                startActivity(intent);
                finish();
            }



        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
