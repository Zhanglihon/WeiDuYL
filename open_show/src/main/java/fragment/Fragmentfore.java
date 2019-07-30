package fragment;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.open_show.R;
import com.example.open_show.R2;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import presenter.ByXqPresenter;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.ByXiangqingBean;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;

public class Fragmentfore extends Fragment {


    TextView textTite, textName,text_bingzheng,text_keshi,text_bgxq
            ,text_shijian,text_yiyuan,text_jingli,text_pnum,text_snum
            ,text_hb,text_yjcount,text_yjtime,text_yjhbi,text_jyname;
    RelativeLayout relativeLayout;
    ImageView image_view,image_haid_f4;
    SimpleDraweeView simpleDraweeView;
    LinearLayout linearLayout;
    List<ByXiangqingBean> list = new ArrayList<>();
    private int sickCircleId;
    private int commentNum;

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
        Fresco.initialize(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LoginBeanDao dao = DaoMaster.newDevSession(getActivity(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        List<LoginBean> loginBeans = dao.loadAll();
        long id = loginBeans.get(0).getId();
        String headPic = loginBeans.get(0).getHeadPic();
        Glide.with(getActivity()).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(image_haid_f4);
        String sessionId = loginBeans.get(0).getSessionId();
        ByXqPresenter byXqPresenter = new ByXqPresenter(new reqewst());
        byXqPresenter.reqeust(id, sessionId, sickCircleId + "");
        if(commentNum!=0){
            linearLayout.setVisibility(View.VISIBLE);
            text_hb.setText(commentNum+"H");

        }else{
            linearLayout.setVisibility(View.GONE);
        }

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

    private class reqewst implements DataCall<ByXiangqingBean>{
        @Override
        public void success(ByXiangqingBean data, Object... args) {
           textTite.setText(data.getTitle());
           textName.setText(data.getAuthorUserId()+"");
           text_bgxq.setText(data.getDetail());
           text_bingzheng.setText("");
           text_keshi.setText(data.getDepartment());
            long adoptTime = data.getAdoptTime();
            String treatmentStartTim = data.getTreatmentStartTime();
            String treatmentEndTim = data.getTreatmentEndTime();
            long l = Long.parseLong(treatmentStartTim);
            long j = Long.parseLong(treatmentEndTim);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            String treatmentStartTime = formatter.format(l);
            String treatmentEndTime = formatter.format(j);
            text_shijian.setText(treatmentStartTime+"-"+treatmentEndTime);
            text_yiyuan.setText(data.getTreatmentHospital());
            text_jingli.setText(data.getTreatmentProcess());
            text_snum.setText(data.getCollectionNum()+"");
            text_pnum.setText(data.getCommentNum()+"");
            String picture = data.getPicture();
            Log.e("aaa",picture.length()+"");
            if(picture.length()!=0) {
                image_view.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(picture).into(image_view);
            }else {
                image_view.setVisibility(View.GONE);
            }
            String adoptTim = formatter.format(adoptTime);
            String adoptHeadPic = data.getAdoptHeadPic();

            if(adoptHeadPic.length()!=0){
                relativeLayout.setVisibility(View.VISIBLE);
                text_yjcount.setVisibility(View.VISIBLE);
                simpleDraweeView.setImageURI(adoptHeadPic);
                text_yjcount.setText(data.getAdoptComment());
                text_yjtime.setText(adoptTim);
                int i = commentNum / 2;
                text_yjhbi.setText("获得"+i+"币");
                text_jyname.setText(data.getAdoptNickName());
            }else{
                relativeLayout.setVisibility(View.GONE);
                text_yjcount.setVisibility(View.GONE);
            }
        }


        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
