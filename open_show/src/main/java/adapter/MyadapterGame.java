package adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.open_show.R;
import com.example.open_show.R2;
import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;
import fragment.Fragmentthree;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.GameBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.PingBean;
import zhang.bw.com.common.bean.PriceBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindUserWallet1;
import zhang.bw.com.common.core.FindVideoCommentList;
import zhang.bw.com.common.core.VideoBuy;
import zhang.bw.com.common.core.exception.ApiException;

public class MyadapterGame extends RecyclerView.Adapter<MyadapterGame.VideoViewHolder> {
    List<GameBean> list = new ArrayList<>();
    Context context;
    boolean aa = true;
    boolean bb = true;
    private FindUserWallet1 findUserWallet1;
    private LoginBean loginBean;
    private TextView text_price;
    private Object data1;
    private VideoBuy videoBuy;
    private TextView textView1;

    public MyadapterGame(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyadapterGame.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.game,parent,false);
       return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyadapterGame.VideoViewHolder holder, int position) {
        loginBean = DaoMaster.newDevSession(context,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        findUserWallet1 = new FindUserWallet1(new Backv());
        findUserWallet1.reqeust(loginBean.getId(),loginBean.getSessionId());
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        holder.mp_video.setUp(list.get(position).shearUrl, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        holder.textView1.setText(list.get(position).title);
        holder.textView2.setText(list.get(position).abstracts);
        holder.ren_text.setText(list.get(position).buyNum+"万人已购买");
        Glide.with(context).load(list.get(position).shearUrl).into(holder.mp_video.thumbImageView);
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aa){
                    aa = false;
                    holder.message.setBackgroundResource(R.mipmap.common_icon_close_live_commenting_n);
                }else {
                    aa = true;
                    holder.message.setBackgroundResource(R.mipmap.common_icon_open_live_commenting_n);
                }
              bacc.bi(position,list);
            }
        });

        holder.shou_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bb){
                    bb = true;
                    holder.shou_image.setBackgroundResource(R.mipmap.common_button_collection_large_s);
                }else {
                    bb = false;
                    holder.shou_image.setBackgroundResource(R.mipmap.common_button_collection_small_n);
                }
                bach.ba(position,list);
            }
        });
        if(list.get(position).whetherCollection.equals("1"))
        {
            holder.shou_image.setBackgroundResource(R.mipmap.common_button_collection_large_s);
        }
        if(list.get(position).whetherCollection.equals("2"))
        {
            holder.shou_image.setBackgroundResource(R.mipmap.common_button_collection_small_n);
        }
        if(list.get(position).whetherBuy.equals("1")){
            holder.game_image1.setBackgroundResource(R.mipmap.common_icon_comment_samll_s);
        }
        if(list.get(position).whetherBuy.equals("2")){
            holder.game_image1.setBackgroundResource(R.mipmap.common_icon_toll_n);
        }
        holder.game_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).whetherBuy.equals("1")){


                }else if(list.get(position).whetherBuy.equals("2")){
                    View contentView = LayoutInflater.from(context).inflate(R.layout.popuplayout, null);
                    textView1 = contentView.findViewById(R.id.texto);
                    ImageView guan =contentView.findViewById(R.id.guan);
                    Button goumai = contentView.findViewById(R.id.goumai);
                    text_price = contentView.findViewById(R.id.text_price);
                    textView1.setText(list.get(position).price+"H币");
                  PopupWindow  mPopWindow = new PopupWindow(contentView,
                          ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.WRAP_CONTENT,
                          true);
                    mPopWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                    guan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mPopWindow.dismiss();
                        }
                    });
                    goumai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String id = list.get(position).id;
                            int price = list.get(position).price;
                            videoBuy = new VideoBuy(new Backg());
                            videoBuy.reqeust(loginBean.getId(),loginBean.getSessionId(),id,price);
                        }
                    });
                    findUserWallet1.reqeust(loginBean.getId(),loginBean.getSessionId());
                    text_price.setText("我的H币: "+data1.toString()+", H币不足?");
                }
            }

        });
    }
    class Backg implements DataCall{

        @Override
        public void success(Object data, Object... args) {
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class Backv implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            data1 = data;
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<GameBean> data) {
        if(!data.isEmpty()){
            list.clear();
        }
        list.addAll(data);
    }

    public class VideoViewHolder extends BaseRecViewHolder {
        public MyVideoPlayer mp_video;
        public TextView textView1, textView2, ren_text;
        private CheckBox message;
        private CheckBox shou_image;
        private CheckBox game_image1;

        public VideoViewHolder(View rootView) {
            super(rootView);
            message = rootView.findViewById(R.id.message);
            mp_video = rootView.findViewById(R.id.mp_video);
            textView1 = rootView.findViewById(R.id.game_text1);
            textView2 = rootView.findViewById(R.id.game_text3);
            ren_text = rootView.findViewById(R.id.ren_text);
            shou_image = rootView.findViewById(R.id.shou_image);
            game_image1 = rootView.findViewById(R.id.game_image11);
        }
    }
    public interface Bach{
        void ba(int i,List<GameBean> list);
    }
    public Bach bach;

    public void setBach(Bach bach) {
        this.bach = bach;
    }
    public interface Bacc{
        void bi(int i,List<GameBean> list);
    }
    public Bacc bacc;

    public void setBacc(Bacc bacc) {
        this.bacc = bacc;
    }
}
