package zhang.bw.com.open_my.activity;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;

public class WdrwActivity extends WDActivity {

    @BindView(R2.id.wdrw_image_back)
    ImageView wdrwImageBack;
    @BindView(R2.id.node_progress)
    NodeProgressView nodeProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdrw;
    }

    @Override
    protected void initView() {

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
