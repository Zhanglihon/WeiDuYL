package com.example.open_show;

import com.alibaba.android.arouter.facade.annotation.Route;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/19
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_XIANGQING)
public class XingqingAcitivity extends WDActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_xiangqing;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
