package zhang.bw.com.open_my.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.BuyVideoBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.adapter.BuyVideoAdapter;
import zhang.bw.com.open_my.presenter.BuyVideoPresenter;

public class BuyVideoActivity extends WDActivity {

    @BindView(R2.id.BuyVideo_recyclerview)
    RecyclerView BuyVideoRecyclerview;
    @BindView(R2.id.BuyVideo_ysj)
    RelativeLayout BuyVideoYsj;
    @BindView(R2.id.BuyVideo_wsj)
    RelativeLayout BuyVideoWsj;
    @BindView(R2.id.BuyVideo_image_back)
    ImageView BuyVideoImageBack;
    private LoginBean loginBean;
    private BuyVideoAdapter buyVideoAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_video;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        BuyVideoImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loginBean = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        BuyVideoPresenter buyVideoPresenter = new BuyVideoPresenter(new buyvideo());
        //获取布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        BuyVideoRecyclerview.setLayoutManager(layoutManager);
        //创建适配器
        buyVideoAdapter = new BuyVideoAdapter(this);
        BuyVideoRecyclerview.setAdapter(buyVideoAdapter);
        buyVideoPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(), 1, 5);
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

    class buyvideo implements DataCall<List<BuyVideoBean>> {

        @Override
        public void success(List<BuyVideoBean> data, Object... args) {
            if (data.size() == 0) {
                BuyVideoYsj.setVisibility(View.GONE);
            } else {
                BuyVideoWsj.setVisibility(View.GONE);
                buyVideoAdapter.addAll(data);
                buyVideoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
