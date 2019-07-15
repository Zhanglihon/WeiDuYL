package com.bw.open_wallet;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import zhang.bw.com.common.core.WDActivity;
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

    @Override
    protected int getLayoutId() {
        return R.layout.layout_wallet;
    }

    @Override
    protected void initView() {
        //点击事件
        initoncilik();
    }

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
        //提现点击事件
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



}
