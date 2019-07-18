package zhang.bw.com.open_my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

public class WdszActivity extends WDActivity {

    @BindView(R2.id.wdsz_image_back)
    ImageView wdszImageBack;
    @BindView(R2.id.wdsz_image_wdxx)
    ImageView wdszImageWdxx;
    @BindView(R2.id.wdsz_tcdl)
    TextView wdszTcdl;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdsz;
    }

    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(WdszActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
