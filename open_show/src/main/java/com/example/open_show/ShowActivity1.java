package com.example.open_show;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragmentone;
import fragment.Fragmentthree;
import fragment.Fragmenttwo;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity1 extends AppCompatActivity {
    @BindView(R2.id.show1_btn1)
    RadioButton show_btn1;
    @BindView(R2.id.show1_btn2)
    RadioButton show_btn2;
    @BindView(R2.id.show1_frame)
    FrameLayout show1_frame;
    private List<Fragment> fragmentq = new ArrayList<>();
    private LoginBeanDao dao;
    private List<LoginBean> loginBeans;
    private String headPic;
    @BindView(R2.id.show_tx)
    SimpleDraweeView show_tx;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show1);
        ButterKnife.bind(this);
        String text = getIntent().getStringExtra("text");
        fragmentq.add(new Fragment1());
        fragmentq.add(new Fragment2());
        if(text.equals("1")){
            show_btn1.setTextColor(Color.parseColor("#87B9F3"));
            getSupportFragmentManager().beginTransaction().add(R.id.show1_frame,fragmentq.get(0)).commit();
        }
        if(text.equals("2")){
            show_btn2.setTextColor(Color.parseColor("#87B9F3"));
            getSupportFragmentManager().beginTransaction().add(R.id.show1_frame,fragmentq.get(1)).commit();
        }
        dao = DaoMaster.newDevSession(ShowActivity1.this,LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = dao.loadAll();

        if (loginBeans.size() != 0) {
            headPic = loginBeans.get(0).getHeadPic();
            Log.e("aaa", headPic);
            show_tx.setImageURI(headPic);
        }
    }
    @OnClick({R2.id.show1_btn1, R2.id.show1_btn2})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.show1_btn1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.show1_frame, fragmentq.get(0)).commit();
            show_btn2.setChecked(false);
            if(show_btn1.isChecked()){
                show_btn1.setTextColor(Color.parseColor("#87B9F3"));
            }
            show_btn2.setTextColor(Color.parseColor("#333333"));

        } else if (i == R.id.show1_btn2) {
            if(show_btn2.isChecked()){
                show_btn2.setTextColor(Color.parseColor("#87B9F3"));
            }
            show_btn1.setTextColor(Color.parseColor("#333333"));
            getSupportFragmentManager().beginTransaction().replace(R.id.show1_frame, fragmentq.get(1)).commit();
            show_btn1.setChecked(false);


        }
    }
}
