package fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.open_show.R;
import com.example.open_show.R2;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import adapter.PingLunlpAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import presenter.ByXqPresenter;
import presenter.FaBiaopl;
import presenter.Guanjianzi;
import presenter.PingLunlbPresenter;
import presenter.ShouCangByq;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.ByXiangqingBean;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.PingLunBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;

public class Fragmentfore extends Fragment {


    TextView textTite, textName, text_bingzheng, text_keshi, text_bgxq, text_shijian, text_yiyuan, text_jingli, text_pnum, text_snum, text_hb, text_yjcount, text_yjtime, text_yjhbi, text_jyname;
    RelativeLayout relativeLayout;
    ImageView image_view, image_haid_f4, image_pinglun, image_viewx,image_view_meiyou,image_shoucang;
    SimpleDraweeView simpleDraweeView;
    LinearLayout linearLayout, linear_layout;
    List<ByXiangqingBean> list = new ArrayList<>();
    XRecyclerView recyc_view3;
    EditText edit_shuru;
    private int sickCircleId;
    private int commentNum;
    private List<LoginBean> loginBeans;
    private long id;
    private int lidsfe;
    PingLunlpAdapter pingLunlpAdapter;
    int page =1;
    private FaBiaopl faBiaopl;
    private String sessionId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfore, container, false);

        EventBus.getDefault().register(this);
        textName = view.findViewById(R.id.text_name);
        textTite = view.findViewById(R.id.text_titel);
        text_bingzheng = view.findViewById(R.id.text_bingzheng);
        simpleDraweeView = view.findViewById(R.id.simpleDraweeView);
        text_keshi = view.findViewById(R.id.text_keshi);
        text_shijian = view.findViewById(R.id.text_shijian);
        text_bgxq = view.findViewById(R.id.text_bgxq);
        text_yiyuan = view.findViewById(R.id.text_yiyuan);
        text_jingli = view.findViewById(R.id.text_jingli);
        text_pnum = view.findViewById(R.id.text_pnum);
        text_snum = view.findViewById(R.id.text_snum);
        linearLayout = view.findViewById(R.id.linearLayout);
        image_view = view.findViewById(R.id.image_view_1);
        text_hb = view.findViewById(R.id.text_hb);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        text_yjcount = view.findViewById(R.id.text_yjcount);
        text_yjhbi = view.findViewById(R.id.text_yjhbi);
        text_jyname = view.findViewById(R.id.text_jyname);
        image_haid_f4 = view.findViewById(R.id.image_haid_f4);
        image_pinglun = view.findViewById(R.id.image_pinglun);
        linear_layout = view.findViewById(R.id.linear_layout);
        recyc_view3 = view.findViewById(R.id.recyc_view3);
        image_viewx = view.findViewById(R.id.image_viewx);
        edit_shuru = view.findViewById(R.id.edit_shuru);
        text_yjtime = view.findViewById(R.id.text_yjtime);
        image_shoucang = view.findViewById(R.id.image_shoucang);


        Fresco.initialize(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LoginBeanDao dao = DaoMaster.newDevSession(getActivity(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = dao.loadAll();
        id = loginBeans.get(0).getId();
        String headPic = loginBeans.get(0).getHeadPic();
        Glide.with(getActivity()).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(image_haid_f4);
        sessionId = loginBeans.get(0).getSessionId();
        ByXqPresenter byXqPresenter = new ByXqPresenter(new reqewst());
        byXqPresenter.reqeust(id, sessionId, sickCircleId + "");
        if (commentNum != 0) {
            linearLayout.setVisibility(View.VISIBLE);
            text_hb.setText(commentNum + "H");

        } else {
            linearLayout.setVisibility(View.GONE);
        }


        //弹出pupopwindo
        image_pinglun.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                // 用于PopupWindow的View
                View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow, null, false);

                // 创建PopupWindow对象，其中：
                // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
                // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
                PopupWindow window = new PopupWindow(contentView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
                // 设置PopupWindow的背景
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // 设置PopupWindow是否能响应外部点击事件
                window.setOutsideTouchable(true);
                // 设置PopupWindow是否能响应点击事件
                window.setTouchable(true);
                // 显示PopupWindow，其中：
                // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
                window.showAsDropDown(linear_layout);
                // 或者也可以调用此方法显示PopupWindow，其中：
                // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
                // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
                // window.showAtLocation(parent, gravity, x, y);
                edit_shuru = contentView.findViewById(R.id.edit_shuru);
                recyc_view3 = contentView.findViewById(R.id.recyc_view3);
                image_viewx = contentView.findViewById(R.id.image_viewx);
                image_view_meiyou = contentView.findViewById(R.id.image_view_meiyou);
                recyc_view3.setLoadingMoreEnabled(true);
                recyc_view3.setPullRefreshEnabled(true);
                //请求数据
                //请求评论列表
                PingLunlbPresenter pingLunlbPresenter  = new PingLunlbPresenter(new questst());

                pingLunlbPresenter.reqeust(id,sessionId,lidsfe+"",page+"",5+"");
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyc_view3.setLayoutManager(layoutManager);
                pingLunlpAdapter = new PingLunlpAdapter(getActivity());
                recyc_view3.setAdapter(pingLunlpAdapter);
                linear_layout.setBackgroundColor(Color.parseColor("#999999")) ;


                recyc_view3.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page++; ;
                        pingLunlbPresenter.reqeust(id,sessionId,lidsfe+"",page+"",5+"");
                    }

                    @Override
                    public void onLoadMore() {
                        page=1;
                        pingLunlbPresenter.reqeust(id,sessionId,lidsfe+"",page+"",5+"");
                    }
                });

                //关闭
                image_viewx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();

                        linear_layout.setBackgroundColor(Color.parseColor("#ffffff")) ;
                    }
                });

                faBiaopl = new FaBiaopl(new fabiao());

                //请求关键字

                //判断

                    edit_shuru.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                                String ss = edit_shuru.getText().toString();
                                Log.e("aaa",ss+"===="+lidsfe+"==="+sickCircleId);
                                faBiaopl.reqeust(id,sessionId,lidsfe+"",ss);

                                return false;
                            }
                            edit_shuru.setText("");
                            return false;
                        }
                    });
                }

        });
        Log.e("aaa",sickCircleId+"++++++++"+id+"======"+sessionId);
        ShouCangByq shouCangByq = new ShouCangByq(new redsafe());
        image_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("aaa",sickCircleId+"++++++++"+id+"======"+sessionId);
                Glide.with(getActivity()).load(R.mipmap.common_button_collection_large_s).into(image_shoucang);
                shouCangByq.reqeust(id,sessionId,sickCircleId+"");
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void listjie(Byliebiao byliebiao) {


        sickCircleId = byliebiao.getSickCircleId();
        commentNum = byliebiao.getCommentNum();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class reqewst implements DataCall<ByXiangqingBean> {



        @Override
        public void success(ByXiangqingBean data, Object... args) {
            //赋值
            lidsfe = data.getSickCircleId();
            textTite.setText(data.getTitle());
            textName.setText(data.getAuthorUserId()+"");
            text_bgxq.setText(data.getDetail());
            text_bingzheng.setText("");
            text_keshi.setText(data.getDepartment());
            long k = data.getAdoptTime();
            String treatmentStartTim = data.getTreatmentStartTime();
            String treatmentEndTim = data.getTreatmentEndTime();
            long l = Long.parseLong(treatmentStartTim);
            long j = Long.parseLong(treatmentEndTim);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            String treatmentStartTime = formatter.format(l);
            String treatmentEndTime = formatter.format(j);
            text_shijian.setText(treatmentStartTime + "-" + treatmentEndTime);
            text_yiyuan.setText(data.getTreatmentHospital());
            text_jingli.setText(data.getTreatmentProcess());
            text_snum.setText(data.getCollectionNum() + "");
            text_pnum.setText(data.getCommentNum() + "");
            String picture = data.getPicture();
            if (picture.length() != 0) {
                image_view.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(picture).into(image_view);
            } else {
                image_view.setVisibility(View.GONE);
            }

            int adoptFlag = data.getAdoptFlag();
            Log.e("aaa",adoptFlag+"");
            if(adoptFlag==1){
                Glide.with(getActivity()).load(R.mipmap.common_button_collection_large_s).into(image_shoucang);
            }else{
                Glide.with(getActivity()).load(R.mipmap.common_button_collection_small_n).into(image_shoucang);
            }
            String adoptTim = formatter.format(k);
                Log.e("aaa",adoptTim+"");
                simpleDraweeView.setImageURI(data.getAdoptHeadPic());
                text_yjcount.setText(data.getAdoptComment());
                text_yjtime.setText(adoptTim+"");
                int i = commentNum / 2;
                text_yjhbi.setText("获得" + i + "币");
                text_jyname.setText(data.getAdoptNickName());




        }


        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class questst implements DataCall<List<PingLunBean>> {
        @Override
        public void success(List<PingLunBean> data, Object... args) {
            recyc_view3.loadMoreComplete();
            recyc_view3.refreshComplete();

            if(data.size()==0){
                edit_shuru.setHint("暂无评论，快来抢沙发！！");
                recyc_view3.setVisibility(View.INVISIBLE);
                image_view_meiyou.setVisibility(View.VISIBLE);

            }else{
                edit_shuru.setHint("在此留下高见吧！！");
                recyc_view3.setVisibility(View.VISIBLE);
                image_view_meiyou.setVisibility(View.GONE);
                pingLunlpAdapter.addalter(data);
                pingLunlpAdapter.notifyDataSetChanged();
            }


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class fabiao implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
           Toast.makeText(getActivity(), "发表成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class redsafe implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            //
            Log.e("aaa","收藏成功");
            Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
