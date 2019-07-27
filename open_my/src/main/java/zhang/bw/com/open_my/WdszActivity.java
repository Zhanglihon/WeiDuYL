package zhang.bw.com.open_my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;

public class WdszActivity extends WDActivity {

    @BindView(R2.id.wdsz_image_back)
    ImageView wdszImageBack;
    @BindView(R2.id.wdsz_image_wdxx)
    ImageView wdszImageWdxx;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdsz;
    }

    @Override
    protected void initView() {
        wdszImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wdszImageWdxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WdszActivity.this,WdxxActivity.class);
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
