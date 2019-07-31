package fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.open_show.R;
import com.example.open_show.R2;

import java.util.ArrayList;
import java.util.List;

import adapter.BingYouAdaoter;
import adapter.MingAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.Byoulb;
import presenter.Guanjianzi;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.MingPresenter;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

public class Fragmenttwo extends WDFragment {

    @BindView(R2.id.recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    ImageView image_hide;
    @BindView(R2.id.text_k1)
    TextView textK1;
    @BindView(R2.id.image_sou)
    ImageView imageSou;
    @BindView(R2.id.edit_text)
    EditText editText;
    @BindView(R2.id.linearlayout)
    LinearLayout linearlayout;
    @BindView(R2.id.image_haid_f2)
    ImageView imageHaidF2;
    List<Byliebiao> list = new ArrayList<>();

    private MingAdapter mingAdapter;
    private Byoulb byoulb;
    private BingYouAdaoter bingYouAdaoter;
    private String headPic;
    private List<LoginBean> loginBeans;
    private Guanjianzi guanjianzi;
    private LinearLayoutManager linearLayoutManagers;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmenttwo;

    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {

        LoginBeanDao dao = DaoMaster.newDevSession(getActivity(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = dao.loadAll();


        if (loginBeans.size() != 0) {
            headPic = loginBeans.get(0).getHeadPic();
            Log.e("aaa", headPic);
            Glide.with(getActivity()).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageHaidF2);
        }

        //请求科目
        MingPresenter mingPresenter = new MingPresenter(new requestss());
        mingPresenter.reqeust();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
        mingAdapter = new MingAdapter(getActivity());
        recyclerView1.setAdapter(mingAdapter);


        //请求列表
        byoulb = new Byoulb(new bingyou());
        byoulb.reqeust(7 + "", 1 + "", 5 + "");
        linearLayoutManagers = new LinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagers);
        bingYouAdaoter = new BingYouAdaoter(getActivity());
        recyclerView.setAdapter(bingYouAdaoter);

        //接口回调
        mingAdapter.setMyCallBack(new MingAdapter.MyCallBack() {
            @Override
            public void oncelicks(int id, String name) {

                list.clear();
                textK1.setText(name);
                byoulb.reqeust(id + "", 1 + "", 5 + "");
                recyclerView.setLayoutManager(linearLayoutManagers);
                recyclerView.setAdapter(bingYouAdaoter);
            }
        });


        //请求关键字
        guanjianzi = new Guanjianzi(new requestst());
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String s = editText.getText().toString();
                    guanjianzi.reqeust(s);
                    editText.setText("");
                    return false;
                }
                return false;
            }
        });

        //滑动效果
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (dy < 0) {
                    imageHaidF2.setVisibility(View.VISIBLE);
                    textK1.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                    linearlayout.setVisibility(View.VISIBLE);
                }
                if (dy > 0) {
                    imageHaidF2.setVisibility(View.INVISIBLE);
                    textK1.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    linearlayout.setVisibility(View.GONE);
                }

            }
        });


    }

    @OnClick(R2.id.image_sou)
    public void onViewClicked() {
        ARouter.getInstance().build(Constant.ACTIVITY_URL_SOUSUO).navigation();

    }


    private class requestss implements DataCall<List<ShowBean>> {
        @Override
        public void success(List<ShowBean> data, Object... args) {

            for (int i = 0; i < data.size(); i++) {
                data.get(i).textcolor = Color.BLACK;
            }
            data.get(0).textcolor = Color.parseColor("#3087ea");
            mingAdapter.setsssss(data);
            mingAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class bingyou implements DataCall<List<Byliebiao>> {
        @Override
        public void success(List<Byliebiao> data, Object... args) {
            bingYouAdaoter.addalter(data);
            bingYouAdaoter.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class requestst implements DataCall<List<Byliebiao>> {
        @Override
        public void success(List<Byliebiao> data, Object... args) {
            if (data.size() == 0) {
                Toast.makeText(getActivity(), "没有搜索到病症", Toast.LENGTH_SHORT).show();
            } else {
                bingYouAdaoter.addalter(data);
                bingYouAdaoter.notifyDataSetChanged();
            }


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
