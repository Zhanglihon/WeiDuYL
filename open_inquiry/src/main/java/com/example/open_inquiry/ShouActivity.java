package com.example.open_inquiry;

import android.graphics.Color;
import android.text.LoginFilter;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.open_inquiry.adapter.MingAdapter;
import com.example.open_inquiry.adapter.YishengAdaoter;
import com.example.open_inquiry.presenter.MingPresenter;
import com.example.open_inquiry.presenter.YishengPresenter;

import org.greenrobot.greendao.annotation.Id;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.bean.ShowBeans;
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
    RecyclerView recycler_view,recyc_view_1;
    private MingAdapter mingAdapter;
    List<ShowBean> list = new ArrayList<>();
    List<YishengBean> yishengBeans = new ArrayList<>();
    private YishengPresenter yishengPresenter;
    private long id;
    private String sessionId;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_shou;

    }

    @Override
    protected void initView() {
        LoginBeanDao dao = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao();
        List<LoginBean> loginBeans = dao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();
        recycler_view = findViewById(R.id.recyc_view);
        recyc_view_1 = findViewById(R.id.recyc_view_1);
        //请求医生
        yishengPresenter = new YishengPresenter(new requests());
        //请求科目
         MingPresenter mingPresenter= new MingPresenter(new requestss());
        mingPresenter.reqeust();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(linearLayoutManager);
        mingAdapter = new MingAdapter(list,this);
        recycler_view.setAdapter(mingAdapter);

        mingAdapter.setMyCallBack(new MingAdapter.MyCallBack() {

            private YishengAdaoter yishengAdaoter;

            @Override
            public void oncelicks(int i) {
                Log.e("aaa",id+"==="+sessionId);
                yishengPresenter.reqeust(id, sessionId,i+"",1+"",1+"",1+"",5+"");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShouActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyc_view_1.setLayoutManager(linearLayoutManager);
                yishengAdaoter = new YishengAdaoter(ShouActivity.this);
                recyc_view_1.setAdapter(yishengAdaoter);

            }
        });


    }

    @Override
    protected void destoryData() {

    }

    private class requestss implements DataCall<List<ShowBean>>{
        @Override
        public void success(List<ShowBean> data, Object... args) {
//            list.addAll(data);
            for (int i = 0; i <data.size() ; i++) {
                data.get(i).textcolor=Color.BLACK;
            }
            data.get(0).textcolor=Color.parseColor("#3087ea");
            mingAdapter.setsssss(data);
            mingAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("aaa",data.getDisplayMessage());
        }
    }

    private class requests implements DataCall<List<YishengBean>>{


        @Override
        public void success(List<YishengBean> data, Object... args) {




        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
