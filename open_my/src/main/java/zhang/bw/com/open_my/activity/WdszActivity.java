package zhang.bw.com.open_my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.presenter.WdxxPresenter;

public class WdszActivity extends WDActivity {

    @BindView(R2.id.wdsz_image_back)
    ImageView wdszImageBack;
    @BindView(R2.id.wdsz_image_wdxx)
    ImageView wdszImageWdxx;
    @BindView(R2.id.wdsz_tcdl)
    TextView wdszTcdl;
    @BindView(R2.id.wdsz_image_tx)
    ImageView wdszImageTx;
    @BindView(R2.id.wdsz_name)
    TextView wdszName;
    @BindView(R2.id.wdsz_uppwd)
    ImageView wdszUppwd;
    @BindView(R2.id.wdsz_LED)
    ImageView wdszLED;
    private LoginBean loginBean;
    private WdxxPresenter wdxxPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdsz;
    }

    @Override
    protected void initView() {
        //屏幕亮度
        wdszLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WdszActivity.this,ScreenActivity.class);
                startActivity(intent);
            }
        });
        //修改密码
        wdszUppwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdszActivity.this, UppwdActivity.class);
                startActivity(intent);
            }
        });
        loginBean = DaoMaster.newDevSession(WdszActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        wdszTcdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBean.setDatas(0);
                ARouter.getInstance().build(Constant.ACTIVITY_URL_SHOW).navigation();
                finish();
            }
        });
        wdszImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wdszImageWdxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdszActivity.this, WdxxActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        wdxxPresenter = new WdxxPresenter(new wdxx());
        wdxxPresenter.reqeust(loginBean.getId(), loginBean.getSessionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class wdxx implements DataCall<CXBean> {

        @Override
        public void success(CXBean data, Object... args) {
            Glide.with(WdszActivity.this).load(data.headPic).apply(RequestOptions.circleCropTransform()).into(wdszImageTx);
            wdszName.setText(data.nickName);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
