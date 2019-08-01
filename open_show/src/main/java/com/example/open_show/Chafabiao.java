package com.example.open_show;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapter.BingYouAdaoter;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.ChaFabiaoPresenter;
import retrofit2.http.Header;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/23
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_CHAFABIAO)
public class Chafabiao extends WDActivity {

    @Autowired
    public String headPic;
    @Autowired
    public int commentUserId;
    @BindView(R2.id.image_beijin)
    ImageView imageBeijin;
    @BindView(R2.id.image_view_toux)
    SimpleDraweeView imageViewToux;
    @BindView(R2.id.recyc_view4)
    XRecyclerView recycView4;
    private BingYouAdaoter bingYouAdaoter;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_chafabiao;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {

        ARouter.getInstance().inject(this);
        imageViewToux.setImageURI(headPic);
       ChaFabiaoPresenter chaFabiaoPresenter = new ChaFabiaoPresenter(new request());
       chaFabiaoPresenter.reqeust(commentUserId+"", 1+"", 10+"");
        Log.e("aaa",commentUserId+"=======");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycView4.setLayoutManager(linearLayoutManager);
        bingYouAdaoter = new BingYouAdaoter(this);
        recycView4.setAdapter(bingYouAdaoter);

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

    private class request implements DataCall<List<Byliebiao>> {
        @Override
        public void success(List<Byliebiao> data, Object... args) {
            bingYouAdaoter.addalte(data);
            bingYouAdaoter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
