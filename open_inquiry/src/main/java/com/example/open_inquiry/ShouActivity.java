package com.example.open_inquiry;

import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.open_inquiry.adapter.MingAdapter;
import com.example.open_inquiry.adapter.MyDialog;
import com.example.open_inquiry.adapter.YishengAdaoter;
import com.example.open_inquiry.presenter.MingPresenter1;
import com.example.open_inquiry.presenter.XiangActivity;
import com.example.open_inquiry.presenter.YishengPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.bean.YishengBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDoctorList;
import zhang.bw.com.common.core.FindDoctorList1;
import zhang.bw.com.common.core.FindDoctorList2;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_INSHOW)

public class ShouActivity extends WDActivity {
    @Autowired
    public int ide;
    RecyclerView recycler_view,recyc_view_1;
    private MingAdapter mingAdapter;
    private YishengPresenter yishengPresenter;
    private YishengAdaoter yishengAdaoter;
    private LoginBean loginBean;
    @BindView(R2.id.image_ren)
    SimpleDraweeView image_ren;
    @BindView(R2.id.text_name)
    TextView text_name;
    @BindView(R2.id.text_zhiwu)
    TextView text_zhiwu;
    @BindView(R2.id.text_adrss)
    TextView text_adrss;
    @BindView(R2.id.text_haopin)
    TextView text_haopin;
    @BindView(R2.id.text_num)
    TextView text_num;
    @BindView(R2.id.text_cishu)
    TextView text_cishu;
    @BindView(R2.id.xiaoxi)
    ImageView xiaoxi;
    private String doctorId;
    @BindView(R2.id.but_zixun)
    Button but_zixun;
    private int servicePrice;
    private MyDialog dialog;
    @BindView(R2.id.hao)
    RadioButton hao;
    @BindView(R2.id.zishu)
    RadioButton zishu;
    @BindView(R2.id.jiage)
    RadioButton jiage;
    @BindView(R2.id.rb_title_select)
    RadioButton rb_title_select;
    @BindView(R2.id.ian)
    ImageView ian;
    @BindView(R2.id.di)
    ImageView di;
    private FindDoctorList findDoctorList;
    private FindDoctorList1 findDoctorList1;
    private FindDoctorList2 findDoctorList2;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_shou;
    }

    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(ShouActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        recycler_view = findViewById(R.id.recyc_view);
        recyc_view_1 = findViewById(R.id.recyc_view_1);
        TextPaint paint2 = text_name.getPaint();
        paint2.setFakeBoldText(true);
        //请求科目
               MingPresenter1 mingPresenter= new MingPresenter1(new Backm());
                mingPresenter.reqeust();
        //请求医生
        yishengPresenter = new YishengPresenter(new Bckp());
                yishengPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(), ide +"",1+"",1+"",1+"",5+"");
                mingAdapter = new MingAdapter(this);
                 recycler_view.setAdapter(mingAdapter);
                recycler_view.setLayoutManager(new LinearLayoutManager(ShouActivity.this,LinearLayoutManager.HORIZONTAL,false));
                mingAdapter.setMyCallBack(new MingAdapter.MyCallBack() {
                    @Override
               public void oncelicks(int i, List<ShowBean> list) {
                        ide = list.get(i).id;
                        yishengPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(), ide +"",1+"",1+"",1+"",5+"");

            }
        });

        yishengAdaoter = new YishengAdaoter(ShouActivity.this);
        recyc_view_1.setAdapter(yishengAdaoter);
        recyc_view_1.setLayoutManager(new LinearLayoutManager(ShouActivity.this,LinearLayoutManager.HORIZONTAL,false));
        yishengAdaoter.setBaop(new YishengAdaoter.Baop() {
            @Override
            public void bop(int i, List<YishengBean> list) {
                servicePrice = list.get(i).servicePrice;
                doctorId = list.get(i).doctorId;
                text_num.setText("服务患者数 "+list.get(i).serverNum);
                text_haopin.setText("好评率 "+list.get(i).praise);
                text_name.setText(list.get(i).doctorName);
                text_zhiwu.setText(list.get(i).jobTitle);
                text_adrss.setText(list.get(i).inauguralHospital);
                text_cishu.setText(list.get(i).servicePrice+"H币/次");
                image_ren.setImageURI(list.get(i).imagePic);
            }
        });
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(doctorId!=null){
                  Intent intent = new Intent(ShouActivity.this,XiangActivity.class);
                  intent.putExtra("oo",doctorId);
                  startActivity(intent);
              }
            }
        });
        but_zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 =View.inflate(ShouActivity.this,R.layout.popw,null);
                TextView  textprice=  view1.findViewById(R.id.textView11);
                textprice.setText("本次咨询将扣除"+servicePrice+"H币!");
                dialog = new MyDialog(ShouActivity.this, 200, 100, view1, R.style.dialog);
                dialog.show();
                final TextView cancel1 =
                        (TextView) view1.findViewById(R.id.cancel1);
                final TextView confirm1 =
                        (TextView)view1.findViewById(R.id.confirm1);
                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                confirm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShouActivity.this,ZixunAcitivty.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
            }
        });
        ian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                di.setVisibility(View.VISIBLE);
                ian.setVisibility(View.GONE);
            }
        });

        rb_title_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yishengPresenter = new YishengPresenter(new Bckp());
                yishengPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(), ide +"",1+"",1+"",1+"",5+"");
            }
        });

        hao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findDoctorList = new FindDoctorList(new Bckp());
                findDoctorList.reqeust(loginBean.getId(), loginBean.getSessionId(), ide +"",2+"",1+"",1+"",5+"");
            }
        });
        zishu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findDoctorList1 = new FindDoctorList1(new Bckp());
                findDoctorList1.reqeust(loginBean.getId(), loginBean.getSessionId(), ide +"",3+"",1+"",1+"",5+"");
            }
        });
        jiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findDoctorList2 = new FindDoctorList2(new Bckp());
                findDoctorList2.reqeust(loginBean.getId(), loginBean.getSessionId(), ide +"",4+"",1+"",1+"",5+"");
            }
        });

    }


    @Override
    protected void destoryData() {

    }

     class Backm implements DataCall<List<ShowBean>> {
        @Override
        public void success(List<ShowBean> data, Object... args) {

            mingAdapter.addAll(data);
            mingAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

     class Bckp implements DataCall<List<YishengBean>> {
        @Override
            public void success(List<YishengBean> data, Object... args) {
               if(data!=null){
                   servicePrice = data.get(0).servicePrice;
                   doctorId = data.get(0).doctorId;
                   text_num.setText("服务患者数 "+data.get(0).serverNum);
                   text_haopin.setText("好评率 "+data.get(0).praise);
                   text_name.setText(data.get(0).doctorName);
                   text_zhiwu.setText(data.get(0).jobTitle);
                   text_adrss.setText(data.get(0).inauguralHospital);
                   text_cishu.setText(data.get(0).servicePrice+"H币/次");
                   image_ren.setImageURI(data.get(0).imagePic);
               }
                yishengAdaoter.adALL(data);
               yishengAdaoter.notifyDataSetChanged();
            }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
