package zhang.bw.com.open_my.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.BingyouBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.adapter.BingYouAdapter;
import zhang.bw.com.open_my.presenter.BingYouPresenter;

public class BingYouActivity extends WDActivity {

    @BindView(R2.id.binyou_image_back)
    ImageView binyouImageBack;
    @BindView(R2.id.binyou_recyclerview)
    RecyclerView binyouRecyclerview;
    @BindView(R2.id.bing_you_ysj)
    RelativeLayout bingYouYsj;
    @BindView(R2.id.bing_you_qfb)
    Button bingYouQfb;
    @BindView(R2.id.bing_you_wsj)
    RelativeLayout bingYouWsj;
    private LoginBean loginBean;
    private BingYouAdapter bingYouAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bing_you;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        //发布
        bingYouQfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_FABIAOPINGLUN).navigation();
            }
        });
        //返回
        binyouImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BingYouPresenter bingYouPresenter=new BingYouPresenter(new bingyou());
        bingYouPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),1,5);
        //获取布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binyouRecyclerview.setLayoutManager(layoutManager);
        //创建适配器
        bingYouAdapter = new BingYouAdapter(this);
        binyouRecyclerview.setAdapter(bingYouAdapter);
    }

    @Override
    protected void destoryData() {

    }
    class bingyou implements DataCall<List<BingyouBean>> {

        @Override
        public void success(List<BingyouBean> data, Object... args) {
            if (data.size()==0){
                bingYouYsj.setVisibility(View.GONE);
            }else {
                bingYouWsj.setVisibility(View.GONE);
            }
            bingYouAdapter.addAll(data);
            bingYouAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(BingYouActivity.this,data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
