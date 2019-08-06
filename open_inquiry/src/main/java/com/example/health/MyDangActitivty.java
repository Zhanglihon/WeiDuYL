package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.ZhengBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.EndInquiry;
import zhang.bw.com.common.core.FindCurrentInquiryRecord;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.open_inquiry.R;
import com.example.open_inquiry.R2;
import com.facebook.drawee.view.SimpleDraweeView;

@Route(path = Constant.ACTIVITY_URL_FABIAOPINGLUN1)
public class MyDangActitivty extends AppCompatActivity {
    @BindView(R2.id.dang_image)
    ImageView dang_image;
    @BindView(R2.id.dang_text)
    TextView dang_text;
    @BindView(R2.id.zheng_image1)
    SimpleDraweeView zheng_image1;
    @BindView(R2.id.zheng_name)
    TextView zheng_name;
    @BindView(R2.id.zheng_zhu)
    TextView zheng_zhu;
    @BindView(R2.id.zheng_ke)
    TextView zheng_ke;
    @BindView(R2.id.zheng_time)
    TextView zheng_time;
    @BindView(R2.id.jiesu)
    TextView jiesu;
    @BindView(R2.id.bn)
    RelativeLayout bn;
    @BindView(R2.id.back1)
    ImageView back1;
    private EndInquiry endInquiry;
    private FindCurrentInquiryRecord findCurrentInquiryRecord;
    private LoginBean loginBean;
    private String recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dang_actitivty);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(MyDangActitivty.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        findCurrentInquiryRecord = new FindCurrentInquiryRecord(new Backa());
        findCurrentInquiryRecord.reqeust(loginBean.getId(),loginBean.getSessionId());
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        endInquiry = new EndInquiry(new Backt());
        jiesu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endInquiry.reqeust(loginBean.getId(),loginBean.getSessionId(),recordId);
                findCurrentInquiryRecord.reqeust(loginBean.getId(),loginBean.getSessionId());
            }
        });
    }
    class Backt implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backa implements DataCall<ZhengBean>{


        @Override
        public void success(ZhengBean data, Object... args) {
            if(data!=null){
                recordId = data.recordId;
                dang_image.setVisibility(View.GONE);
                dang_text.setVisibility(View.GONE);
                zheng_image1.setImageURI(data.imagePic);
                zheng_name.setText(data.doctorName);
                zheng_zhu.setText(data.jobTitle);
                zheng_ke.setText(data.department);
                zheng_time.setText(data.inquiryTime);
            }else {
                dang_image.setVisibility(View.VISIBLE);
                dang_text.setVisibility(View.VISIBLE);
                bn.setVisibility(View.GONE);
                zheng_image1.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
