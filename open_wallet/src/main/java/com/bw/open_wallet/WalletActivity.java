package com.bw.open_wallet;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.open_wallet.adapter.HbAdapter;
import com.bw.open_wallet.prensenter.MemoneyPresenter;
import com.bw.open_wallet.prensenter.XfjlPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.XfjlBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/12 10:28
 * @Description：钱包页面
 */
@Route(path = Constant.ACTIVITY_URL_WALLET)
public class WalletActivity extends WDActivity {
    @BindView(R2.id.image_fan)
    ImageView imageFan;
    @BindView(R2.id.text_title)
    TextView textTitle;
    @BindView(R2.id.text_hb)
    TextView textHb;
    @BindView(R2.id.cicle)
    RelativeLayout cicle;
    @BindView(R2.id.text_h)
    TextView textH;
    @BindView(R2.id.but_tx)
    Button butTx;
    @BindView(R2.id.but_cz)
    Button butCz;
    @BindView(R2.id.recyc_view)
    RecyclerView recycView;
    List<XfjlBean> list = new ArrayList<>();
    private HbAdapter adapter;
    private MemoneyPresenter memoneyPresenter;
    private LoginBean loginBean;
    private XfjlPresenter xfjlPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_wallet;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        //生成数据库
        loginBean = DaoMaster.newDevSession(WalletActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        //点击事件
        initoncilik();
        //消费记录
        xfjlPresenter = new XfjlPresenter(new request());
        xfjlPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),1,10);

        //请求H币
        memoneyPresenter = new MemoneyPresenter(new money());
        memoneyPresenter.reqeust(loginBean.getId(),loginBean.getSessionId());


        //适配器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycView.setLayoutManager(layoutManager);
        adapter = new HbAdapter(list,this);
        recycView.setAdapter(adapter);
    }

    @SuppressLint("WrongConstant")
    private void initoncilik() {
        imageFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //充值点击事件
        butCz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_RECHARGE).navigation();
            }
        });
        //提现点事件
        butTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_EMBODY).navigation();
            }
        });


    }

    @Override
    protected void destoryData() {

    }
    class request implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            List<XfjlBean> listResult = (List<XfjlBean>) data;
            list.addAll(listResult);
            adapter.setData(listResult);
            adapter.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private class money implements DataCall<Integer>{


        @Override
        public void success(Integer data, Object... args) {
            textHb.setText(data+"");
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("aaa",data.getDisplayMessage());
        }
    }
}
