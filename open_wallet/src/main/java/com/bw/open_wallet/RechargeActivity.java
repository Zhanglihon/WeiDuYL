package com.bw.open_wallet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R2.id.image_fan)
    ImageView imageFan;
    @BindView(R2.id.qian)
    TextView qian;
    EditText editText;
    @BindView(R2.id.radio_wx)
    RadioButton radioWx;
    @BindView(R2.id.radio_zfb)
    RadioButton radioZfb;
    @BindView(R2.id.but_cz)
    Button butCz;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recharge;
    }

    @Override
    protected void initView() {
        editText.findViewById(R.id.edit_text);

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
