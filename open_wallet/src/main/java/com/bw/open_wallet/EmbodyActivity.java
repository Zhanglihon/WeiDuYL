package com.bw.open_wallet;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.open_wallet.prensenter.MemoneyPresenter;
import com.bw.open_wallet.prensenter.TxPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
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
    @BindView(R2.id.embody_hb)
    TextView embodyHb;
    @BindView(R2.id.embody_tx)
    TextView embodyTx;
    @BindView(R2.id.but_tx)
    Button butTx;
    private MemoneyPresenter memoneyPresenter;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_embody;

    }

    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(EmbodyActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        text_jilu = findViewById(R.id.text_jilu);
        image_fan = findViewById(R.id.image_fan);
        edit_text = findViewById(R.id.edit_text);
        memoneyPresenter = new MemoneyPresenter(new money());
        memoneyPresenter.reqeust(loginBean.getId(), loginBean.getSessionId());
        //点击事件
        initonclick();
        butTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = Integer.parseInt(edit_text.getText().toString());
                TxPresenter txPresenter=new TxPresenter(new tx());
                if (money<2000){
                    Toast.makeText(EmbodyActivity.this, "提现H币不能小于2000", Toast.LENGTH_SHORT).show();
                }else{
                    txPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),money);
                }

            }
        });
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private class money implements DataCall<Integer> {


        @Override
        public void success(Integer data, Object... args) {
            embodyHb.setText(data + "H币,");
            embodyTx.setText("可提现" + data / 100 + "元。");
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("aaa", data.getDisplayMessage());
        }
    }
    class tx implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(EmbodyActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
