package fragment;

import android.annotation.SuppressLint;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.open_show.R;
import com.example.open_show.R2;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import adapter.MyAdapter1;
import adapter.MyjiKangAdapter1;
import adapter.MyjikangAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import zhang.bw.com.common.bean.BannerBean;
import zhang.bw.com.common.bean.JanBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.core.BannerPresenter;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDepartmentPresenter;
import zhang.bw.com.common.core.FindInformationList;
import zhang.bw.com.common.core.FindInformationPlateList;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

public class Fragmentone extends WDFragment {
    @BindView(R2.id.one_text1)
    TextView one_text1;
    @BindView(R2.id.one_text2)
    TextView one_text2;
    @BindView(R2.id.edit)
    EditText edit;
    @BindView(R2.id.xBanner)
    XBanner xBanner;
    @BindView(R2.id.one_recyc)
    RecyclerView one_recyc;
    @BindView(R2.id.two_recyc)
    RecyclerView two_recyc;
    @BindView(R2.id.three_recyc)
    RecyclerView three_recyc;
    @BindView(R2.id.one_image1)
    ImageView one_image1;
    @BindView(R2.id.one_image2)
    ImageView one_image2;
    @BindView(R2.id.show_tx)
    ImageView showTx;
    private BannerPresenter bannerPresenter;
    private FindDepartmentPresenter findDepartmentPresenter;
    private FindInformationPlateList findInformationPlateList;
    private FindInformationList findInformationList;
    private MyAdapter1 myAdapter1;
    private MyjikangAdapter myjikangAdapter;
    private MyjiKangAdapter1 myjiKangAdapter1;
    private String id11;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmentone;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        showTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_LOGIN).navigation();
            }
        });
        TextPaint paint = one_text1.getPaint();
        paint.setFakeBoldText(true);
        TextPaint paint2 = one_text2.getPaint();
        paint2.setFakeBoldText(true);
        edit.getBackground().setAlpha(100);
        bannerPresenter = new BannerPresenter(new Back1());
        bannerPresenter.reqeust();
        findDepartmentPresenter = new FindDepartmentPresenter(new Back2());
        findDepartmentPresenter.reqeust();
        findInformationPlateList = new FindInformationPlateList(new Back3());
        findInformationPlateList.reqeust();
        myAdapter1 = new MyAdapter1(getContext());
        one_recyc.setAdapter(myAdapter1);
        one_recyc.setLayoutManager(new GridLayoutManager(getContext(), 4));
        myjikangAdapter = new MyjikangAdapter(getContext());
        two_recyc.setAdapter(myjikangAdapter);
        findInformationList = new FindInformationList(new Back4());
        findInformationList.reqeust("1", "1", "5");
        two_recyc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        myjikangAdapter.setJianBack(new MyjikangAdapter.JianBack() {
            @Override
            public void jian(int i, List<MyjiankangBean> list) {
                String id = list.get(i).id;
                findInformationList.reqeust(id, "1", "5");

            }
        });
        myjiKangAdapter1 = new MyjiKangAdapter1(getContext());
        three_recyc.setAdapter(myjiKangAdapter1);
        three_recyc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        one_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        one_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    class Back1 implements DataCall<List<BannerBean>> {

        @Override
        public void success(List<BannerBean> data, Object... args) {
            xBanner.setBannerData(data);
            xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getActivity()).load(data.get(position).imageUrl).into((ImageView) view);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class Back2 implements DataCall<List<ShowBean>> {

        @Override
        public void success(List<ShowBean> data, Object... args) {
            myAdapter1.addAll(data);
            myAdapter1.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class Back3 implements DataCall<List<MyjiankangBean>> {

        @Override
        public void success(List<MyjiankangBean> data, Object... args) {
            myjikangAdapter.addALL(data);
            myjikangAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }

    }

    class Back4 implements DataCall<List<JanBean>> {


        @Override
        public void success(List<JanBean> data, Object... args) {
            myjiKangAdapter1.addALL(data);
            myjiKangAdapter1.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
