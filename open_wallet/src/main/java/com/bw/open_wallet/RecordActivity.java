package com.bw.open_wallet;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/15
 * @Description：提现记录
 */
@Route(path = Constant.ACTIVITY_URL_RECORD)
public class RecordActivity extends WDActivity {
    ImageView image_fan;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_record;
    }

    @Override
    protected void initView() {
        image_fan=findViewById(R.id.image_fan);
        image_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void destoryData() {

    }
}
