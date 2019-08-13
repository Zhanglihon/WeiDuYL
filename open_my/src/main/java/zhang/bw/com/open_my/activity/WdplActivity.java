package zhang.bw.com.open_my.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.WoDeplBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.adapter.WdplAdapter;
import zhang.bw.com.open_my.presenter.WdplPresenter;

public class WdplActivity extends WDActivity {

    @BindView(R2.id.wdpl_image_back)
    ImageView wdplImageBack;
    @BindView(R2.id.wdpl_recyclerview)
    RecyclerView wdplRecyclerview;
    private LoginBean loginBean;
    private WdplAdapter wdplAdapter;
    private WdplPresenter wdplPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdpl;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(WdplActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        //返回
        wdplImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wdplPresenter = new WdplPresenter(new wdpl());
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        //获取布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wdplRecyclerview.setLayoutManager(layoutManager);
        //创建适配器
        wdplAdapter = new WdplAdapter(this);
        wdplRecyclerview.setAdapter(wdplAdapter);
        wdplPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),id,1,5);
    }
    @Override
    protected void destoryData() {

    }
    class wdpl implements DataCall<WoDeplBean> {

        @Override
        public void success(WoDeplBean data, Object... args) {
            wdplAdapter.add(data);
            wdplAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
