package com.example.open_inquiry.presenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.health.ShouActivity;
import com.example.health.adapter.MyDialog;
import com.example.health.adapter.PingAdapter;
import com.example.open_inquiry.R;
import com.example.open_inquiry.R2;
import com.example.open_inquiry.adapter.PingAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.ZixunBean;
import zhang.bw.com.common.core.CancelFollow;
import zhang.bw.com.common.core.ConsultDoctor;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDoctorInfo;
import zhang.bw.com.common.core.FollowDoctor;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

public class XiangActivity extends AppCompatActivity {
    private FindDoctorInfo findDoctorInfo;
    private LoginBean loginBean;
    @BindView(R2.id.a1)
    SimpleDraweeView a1;
    @BindView(R2.id.a2)
    TextView a2;
    @BindView(R2.id.renzhi)
    TextView renzhi;
    @BindView(R2.id.a3)
    TextView a3;
    @BindView(R2.id.jian_text1)
    TextView jian_text1;
    @BindView(R2.id.a4)
    TextView a4;
    @BindView(R2.id.fuwu)
    TextView fuwu;
    @BindView(R2.id.jian_text2)
    TextView jian_text2;
    @BindView(R2.id.ping)
    TextView ping;
    @BindView(R2.id.recyc_ping)
    RecyclerView recyc_ping;
    @BindView(R2.id.shou)
    TextView shou;
    @BindView(R2.id.hi)
    ImageView hi;
    @BindView(R2.id.liji)
    TextView liji;
    private PingAdapter pingAdapter;
    private FollowDoctor followDoctor;
    private CancelFollow cancelFollow;
    private String oo;
    private ConsultDoctor consultDoctor;
    private MyDialog dialog;
    private MyDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(XiangActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        oo = getIntent().getStringExtra("oo");
        findDoctorInfo = new FindDoctorInfo(new Backg());
        findDoctorInfo.reqeust(loginBean.getId(),loginBean.getSessionId(), oo);
        followDoctor = new FollowDoctor(new Backo());
        cancelFollow = new CancelFollow(new Backn());
        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        liji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        loginBean = DaoMaster.newDevSession(XiangActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
                        consultDoctor = new ConsultDoctor(new Backl());
                        consultDoctor.reqeust(loginBean.getId(),loginBean.getSessionId(),oo);

            }
        });

    }
    class Backg implements DataCall<ZixunBean> {
        @SuppressLint("WrongConstant")
        @Override
        public void success(final ZixunBean data, Object... args) {
            if(data.followFlag == 2){
                shou.setBackgroundResource(R.mipmap.common_icon_attention_large_n);

            }
            if(data.followFlag == 1){
                shou.setBackgroundResource(R.mipmap.common_icon_attention_large_s);
            }
            shou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(data.followFlag == 2){
                        followDoctor.reqeust(loginBean.getId(),loginBean.getSessionId(),oo);
                        data.followFlag=2;
                        findDoctorInfo.reqeust(loginBean.getId(),loginBean.getSessionId(), oo);

                    }
                    if(data.followFlag == 1){
                        cancelFollow.reqeust(loginBean.getId(),loginBean.getSessionId(),oo);
                        data.followFlag=1;
                        findDoctorInfo.reqeust(loginBean.getId(),loginBean.getSessionId(), oo);
                    }

                }

            });
            a1.setImageURI(data.imagePic);
            a2.setText(data.doctorName);
            a3.setText(data.inauguralHospital);
            renzhi.setText(data.jobTitle);
            a4.setText("好评率 "+data.praise);
            jian_text1.setText(data.personalProfile);
            fuwu.setText("服务患者数 "+data.serverNum);
            jian_text2.setText(data.goodField);
            ping.setText("用户评价("+data.commentList.size()+"条评价)");
            pingAdapter = new PingAdapter(XiangActivity.this);
            recyc_ping.setAdapter(pingAdapter);
            recyc_ping.setLayoutManager(new LinearLayoutManager(XiangActivity.this,LinearLayoutManager.VERTICAL,false));
            pingAdapter.addAll(data.commentList);
            pingAdapter.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }

    }
    class Backo implements DataCall {

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backn implements DataCall {

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backl implements DataCall<String>{

        @Override
        public void success(String data, Object... args) {
            if(data!=null){
               View view1 = View.inflate(XiangActivity.this,R.layout.popw1,null);
                TextView  textprice=  view1.findViewById(R.id.textView11);
                textprice.setText("你尚有咨询在进行中" +"\n"+
                        "请先关闭再开始新的咨询");
                dialog1 = new MyDialog(XiangActivity.this, 200, 100, view1, R.style.dialog);
                Window window = dialog1.getWindow();
                window.setGravity(Gravity.LEFT);
                dialog1.show();
                final TextView cancel2 =
                        (TextView) view1.findViewById(R.id.cancel2);
                final TextView confirm2 =
                        (TextView) view1.findViewById(R.id.confirm2);
                cancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                confirm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ARouter.getInstance().build(Constant.ACTIVITY_URL_FABIAOPINGLUN1).navigation();
                    }
                });
            }else {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_FABIAOPINGLUNIM).navigation();
                dialog1.dismiss();
                dialog.dismiss();

            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
