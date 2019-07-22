package com.bw.open_wallet;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;
/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/15
 * @Description：提现前页面
 */
@Route(path = Constant.ACTIVITY_URL_EMBODY)
public class EmbodyActivity extends WDActivity {
    ImageView image_fan;
    TextView text_jilu;
    EditText edit_text;
    CheckBox checkbox_1;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_embody;

    }

    @Override
    protected void initView() {
        text_jilu=findViewById(R.id.text_jilu);
        image_fan=findViewById(R.id.image_fan);
        edit_text=findViewById(R.id.edit_text);

        //点击事件
       initonclick();
    }

    private void initonclick() {
        text_jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_RECORD).navigation();
            }
        });
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
