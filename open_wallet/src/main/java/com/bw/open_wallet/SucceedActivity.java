package com.bw.open_wallet;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/15
 * @Description：充值成功页面
 */
@Route(path = Constant.ACTIVITY_URL_SUCCEED)
public class SucceedActivity extends WDActivity {
    TextView text_ljck;
    RecyclerView recyc_view;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_succeed;
    }

    @Override
    protected void initView() {
        text_ljck= findViewById(R.id.text_ljck);
        recyc_view= findViewById(R.id.recyc_view);



    }

    @Override
    protected void destoryData() {

    }
}
