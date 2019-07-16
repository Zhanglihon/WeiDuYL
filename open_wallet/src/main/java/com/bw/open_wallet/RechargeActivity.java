package com.bw.open_wallet;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

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
    @BindView(R2.id.radio_wx)
    RadioButton radioWx;
    @BindView(R2.id.radio_zfb)
    RadioButton radioZfb;
    @BindView(R2.id.but_cz)
    Button butCz;

    EditText editText;
    TextView text_hh;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_recharge;
    }

    @Override
    protected void initView() {
        editText=findViewById(R.id.edit_text);
        text_hh=findViewById(R.id.text_hh);
        //点击事件
        onclicks();

    }

    private void onclicks() {
        imageFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //输入之前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sss = editText.getText().toString();
                Log.e("aaa", sss);

                if (sss.equals("")) {
                    text_hh.setText(0+"");
                } else if(sss.equals(".")){
                }else{
                    float ss = parseFloat(sss);
                    int s1 = (int) (ss * 100);
                    text_hh.setText(s1 + "");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
