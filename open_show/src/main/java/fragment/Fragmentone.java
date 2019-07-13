package fragment;

import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.open_show.R;
import com.example.open_show.R2;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter1;
import adapter.MyjikangAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import zhang.bw.com.common.bean.BannerBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.core.BannerPresenter;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDepartmentPresenter;
import zhang.bw.com.common.core.FindInformationList;
import zhang.bw.com.common.core.FindInformationPlateList;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.exception.ApiException;

public class Fragmentone extends WDFragment {
    @BindView(R2.id.one_text1)
    TextView one_text1;
    @BindView(R2.id.one_text2)
    TextView  one_text2;
    @BindView(R2.id.edit)
    EditText edit;
    @BindView(R2.id.xBanner)
    XBanner xBanner;
    @BindView(R2.id.one_recyc)
    RecyclerView one_recyc;
    @BindView(R2.id.two_recyc)
    RecyclerView two_recyc;
   private BannerPresenter bannerPresenter;
   private FindDepartmentPresenter findDepartmentPresenter;
   private FindInformationPlateList findInformationPlateList;
   private FindInformationList findInformationList;
   private MyAdapter1 myAdapter1;
   private MyjikangAdapter myjikangAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView() {

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
        one_recyc.setLayoutManager(new GridLayoutManager(getContext(),4));
        myjikangAdapter = new MyjikangAdapter(getContext());
        two_recyc.setAdapter(myjikangAdapter);
        two_recyc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        myjikangAdapter.setJianBack(new MyjikangAdapter.JianBack() {
            @Override
            public void jian(int i, List<MyjiankangBean> list) {
                String id = list.get(i).id;
                findInformationList = new FindInformationList(new Back4());
                findInformationList.reqeust(id,"1","5");

            }
        });
    }
    class Back1 implements DataCall<List<BannerBean>>{

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
    class Back2 implements DataCall<List<ShowBean>>{

        @Override
        public void success(List<ShowBean> data, Object... args) {
            myAdapter1.addAll(data);
            myAdapter1.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Back3 implements DataCall<List<MyjiankangBean>>{

        @Override
        public void success(List<MyjiankangBean> data, Object... args) {
            myjikangAdapter.addALL(data);
            myjikangAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }

    }
    class Back4 implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
