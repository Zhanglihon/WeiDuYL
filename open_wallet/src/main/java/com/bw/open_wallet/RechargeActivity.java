package com.bw.open_wallet;

import com.alibaba.android.arouter.facade.annotation.Route;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/12 19:46
 * @Description：充值页面
 */
@Route(path = Constant.ACTIVITY_URL_RECHARGE)
public class RechargeActivity extends WDActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.layout_recharge;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
