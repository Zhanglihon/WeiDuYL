package zhang.bw.com.open_my.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.SuggestBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.adapter.SuggestAdapter;
import zhang.bw.com.open_my.presenter.SuggestPresenter;

public class SuggestActivity extends WDActivity {
    @BindView(R2.id.suggest_image_back)
    ImageView suggestImageBack;
    @BindView(R2.id.suggest_recyclerview)
    RecyclerView suggestRecyclerview;
    @BindView(R2.id.suggest_ysj)
    RelativeLayout suggestYsj;
    @BindView(R2.id.suggest_wsj)
    RelativeLayout suggestWsj;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggest;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        suggestImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        suggestRecyclerview.setLayoutManager(layoutManager);
        //创建适配器
        SuggestAdapter suggestAdapter = new SuggestAdapter(this);
        suggestRecyclerview.setAdapter(suggestAdapter);
        SuggestPresenter suggestPresenter = new SuggestPresenter(new suggest());
        suggestPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(), 1, 5);
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

    class suggest implements DataCall<List<SuggestBean>> {

        @Override
        public void success(List<SuggestBean> data, Object... args) {
            Log.i("h",data+"");
            if (data.size()==0) {
                suggestYsj.setVisibility(View.GONE);
            }else {
                suggestWsj.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
