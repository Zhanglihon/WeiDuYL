package com.bw.open_wallet;

import com.alibaba.android.arouter.facade.annotation.Route;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/15
 * @Description：提现页面
 */
@Route(path = Constant.ACTIVITY_URL_DEPOSIT)
public class DepositActivity extends WDActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.layout_deposit;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
