package com.example.open_inquiry;

import android.graphics.Color;
import android.text.TextPaint;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.open_inquiry.adapter.MingAdapter;
import com.example.open_inquiry.adapter.YishengAdaoter;
import com.example.open_inquiry.presenter.MingPresenter1;
import com.example.open_inquiry.presenter.YishengPresenter;
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
    @Autowired
    public String list;
    RecyclerView recycler_view,recyc_view_1;
    private MingAdapter mingAdapter;
    private YishengPresenter yishengPresenter;
    private YishengAdaoter yishengAdaoter;
    private LoginBean loginBean;
    @BindView(R2.id.image_ren)
    ImageView image_ren;
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

    @Override
    protected int getLayoutId() {
        return R.layout.layout_shou;
    }

    @Override
    protected void initView() {
        Toast.makeText(ShouActivity.this,list+"",Toast.LENGTH_LONG).show();
        loginBean = DaoMaster.newDevSession(ShouActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        recycler_view = findViewById(R.id.recyc_view);
        recyc_view_1 = findViewById(R.id.recyc_view_1);
        TextPaint paint2 = text_name.getPaint();
        paint2.setFakeBoldText(true);
        Toast.makeText(ShouActivity.this,ide+"",Toast.LENGTH_LONG).show();

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
                text_num.setText("服务患者数 "+list.get(i).serverNum);
                text_haopin.setText("好评率 "+list.get(i).praise);
                text_name.setText(list.get(i).doctorName);
                text_zhiwu.setText(list.get(i).jobTitle);
                text_adrss.setText(list.get(i).inauguralHospital);
                text_cishu.setText(list.get(i).servicePrice+"H币/次");
                Glide.with(ShouActivity.this).load(list.get(i).imagePic).into(image_ren);
            }
        });
        yishengAdaoter.setOnRecyclerViewItemClickListener(new YishengAdaoter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                yishengAdaoter.setThisPosition(position);
                yishengAdaoter.notifyDataSetChanged();
            }
        });

    }


    @Override
    protected void destoryData() {

    }

     class Backm implements DataCall<List<ShowBean>>{
        @Override
        public void success(List<ShowBean> data, Object... args) {
            mingAdapter.addAll(data);
            mingAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

     class Bckp implements DataCall<List<YishengBean>>{
        @Override
            public void success(List<YishengBean> data, Object... args) {
                    yishengAdaoter.adALL(data);
                    yishengAdaoter.notifyDataSetChanged();
            }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
