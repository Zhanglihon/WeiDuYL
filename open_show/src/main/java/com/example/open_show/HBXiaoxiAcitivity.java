package com.example.open_show;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.appcompat.app.AppCompatActivity;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/8/8
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_XIAOXI)
public class XiaoXiAcitivity extends WDActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.layout_xiaoxi;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
