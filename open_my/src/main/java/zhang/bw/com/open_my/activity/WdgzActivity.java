package zhang.bw.com.open_my.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.WdgzBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.adapter.WdgzAdapter;
import zhang.bw.com.open_my.presenter.WdgzPresenter;

public class WdgzActivity extends WDActivity {

    @BindView(R2.id.wdgz_image_back)
    ImageView wdgzImageBack;
    @BindView(R2.id.wdgz_recyclerview)
    RecyclerView wdgzRecyclerview;
    @BindView(R2.id.wdgz_ysj)
    RelativeLayout wdgzYsj;
    @BindView(R2.id.wdgz_wsj)
    RelativeLayout wdgzWsj;
    private LoginBean loginBean;
    private WdgzAdapter wdgzAdapter;

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
        loginBean = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        //获取布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wdgzRecyclerview.setLayoutManager(layoutManager);
        //创建适配器
        wdgzAdapter = new WdgzAdapter(this);
        wdgzRecyclerview.setAdapter(wdgzAdapter);
        WdgzPresenter wdgzPresenter = new WdgzPresenter(new wdgz());
        Log.i("aaaa",loginBean.getSessionId());
        wdgzPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),1,5);

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

    class wdgz implements DataCall<List<WdgzBean>> {

        @Override
        public void success(List<WdgzBean> data, Object... args) {
            if (data.size() == 0) {
                wdgzYsj.setVisibility(View.GONE);
            }else {
                wdgzWsj.setVisibility(View.GONE);
                wdgzAdapter.addAll(data);
                wdgzAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(WdgzActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
