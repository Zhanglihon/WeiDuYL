package zhang.bw.com.open_my.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;

public class WdgzActivity extends WDActivity {

    @BindView(R2.id.wdgz_image_back)
    ImageView wdgzImageBack;
    @BindView(R2.id.wdgz_recyclerview)
    RecyclerView wdgzRecyclerview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdgz;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        wdgzImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wdgzRecyclerview.setLayoutManager(layoutManager);
        //创建适配器

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
