package fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.open_show.R;
import com.example.open_show.R2;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import adapter.BingYouAdaoter;
import adapter.MingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.Byoulb;
import presenter.Guanjianzi;
import presenter.MingPresenter;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.exception.ApiException;

public class Fragmenttwo extends WDFragment {
    List<ShowBean> list = new ArrayList<>();

    @BindView(R2.id.recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    ImageView image_hide;
    @BindView(R2.id.image_haid)
    ImageView imageHaid;
    @BindView(R2.id.text_k1)
    TextView textK1;
    @BindView(R2.id.image_sou)
    ImageView imageSou;
    @BindView(R2.id.edit_text)
    EditText editText;
    @BindView(R2.id.linearlayout)
    LinearLayout linearlayout;

    private MingAdapter mingAdapter;
    private Byoulb byoulb;
    private BingYouAdaoter bingYouAdaoter;
    private String headPic;
    private List<LoginBean> loginBeans;
    private Guanjianzi guanjianzi;

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
            Log.e("aaa",headPic);
          //  Glide.with(getActivity()).load(headPic).into(image_hide);
        }

        //请求科目
        MingPresenter mingPresenter = new MingPresenter(new requestss());
        mingPresenter.reqeust();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
        mingAdapter = new MingAdapter(list, getActivity());
        recyclerView1.setAdapter(mingAdapter);


        //请求列表
        byoulb = new Byoulb(new bingyou());
        byoulb.reqeust(7 + "", 1 + "", 5 + "");
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagers);
        bingYouAdaoter = new BingYouAdaoter(getActivity());
        recyclerView.setAdapter(bingYouAdaoter);

        //接口回调
        mingAdapter.setMyCallBack(new MingAdapter.MyCallBack() {
            @Override
            public void oncelicks(int id) {
                byoulb.reqeust(id + "", 1 + "", 5 + "");
                LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity());
                linearLayoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManagers);
                bingYouAdaoter = new BingYouAdaoter(getActivity());
                recyclerView.setAdapter(bingYouAdaoter);
            }

        });


        //请求关键字
        guanjianzi = new Guanjianzi(new requestst());
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    String s = editText.getText().toString();
                    guanjianzi.reqeust(s);
                    editText.setText("");
                    return false;
                }
                return false;
            }
        });


        //病友圈列表接口返回
        bingYouAdaoter.setMyCallBack(new BingYouAdaoter.MyCallBack() {
            @Override
            public void listjieh(Byliebiao listd) {
                Fragmentfore fragmentfore = new Fragmentfore();

            }
        });
    }

    @OnClick(R2.id.image_sou)
    public void onViewClicked() {
       // image_hide.setVisibility(View.VISIBLE);
        textK1.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);
        linearlayout.setVisibility(View.GONE);
    }


    private class requestss implements DataCall<List<ShowBean>> {
        @Override
        public void success(List<ShowBean> data, Object... args) {
            list.addAll(data);
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

    private class requestst implements DataCall<List<Byliebiao>>{
        @Override
        public void success(List<Byliebiao> data, Object... args) {
            if(data.size()==0){
                Toast.makeText(getActivity(), "没有搜索到病症", Toast.LENGTH_SHORT).show();
            }else{
                bingYouAdaoter.addalter(data);
                bingYouAdaoter.notifyDataSetChanged();
            }



        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
