package zhang.bw.com.open_my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.presenter.WdsexPresenter;

public class WdsexActivity extends WDActivity {

    @BindView(R2.id.wdsex_boy)
    ImageView wdsexBoy;
    @BindView(R2.id.wdsex_girl)
    ImageView wdsexGirl;
    @BindView(R2.id.wdsex_image_back)
    ImageView wdsexImageBack;
    @BindView(R2.id.wdsex_wc)
    Button wdsexWc;
    private LoginBean loginBean;
    private WdsexPresenter wdsexPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdsex;
    }

    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(WdsexActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        wdsexPresenter = new WdsexPresenter(new wdsex());
        wdsexBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wdsexWc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wdsexPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(),1+"");
                    }
                });

            }
        });
        wdsexGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wdsexWc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wdsexPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(),2+"");
                    }
                });
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

    class wdsex implements DataCall {

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(WdsexActivity.this, "性别修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(WdsexActivity.this, "性别修改失败", Toast.LENGTH_SHORT).show();
        }
    }
}
