package com.bw.open_wallet;

import com.alibaba.android.arouter.facade.annotation.Route;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

@Route(path = Constant.ACTIVITY_URL_EMBODY)
public class EmbodyActivity extends WDActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.layout_embody;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
