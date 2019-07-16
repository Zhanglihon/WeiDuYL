package com.bw.open_wallet;

import com.alibaba.android.arouter.facade.annotation.Route;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/16
 * @Description：上传身份证
 * ++
 */
@Route(path = Constant.ACTIVITY_URL_SHENGZ)
public class ShengfzActivity extends WDActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.layout_shengfz;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
