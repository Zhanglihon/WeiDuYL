package adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.jzvd.JZVideoPlayerStandard;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.GameBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.core.AddUserVideoCollection;
import zhang.bw.com.common.core.AddVideoComment;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindUserWallet1;
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
    private double data1;
    private VideoBuy videoBuy;
    private TextView textView1;
    private MyDialog dialog;
    private AddVideoComment addVideoComment;
    private TextView button_shu;
    private EditText edit_fa;
    private PopupWindow mPopWindow1;
    private String s;

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
        videoBuy = new VideoBuy(new Backg());
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
        if(list.get(position).whetherCollection == 1)
        {
            holder.shou_image.setBackgroundResource(R.mipmap.common_button_collection_large_s);
        }
        if(list.get(position).whetherCollection == 2)
        {
            holder.shou_image.setBackgroundResource(R.mipmap.common_button_collection_small_n);
        }
        AddUserVideoCollection addUserVideoCollection= new AddUserVideoCollection(new Backh());
        holder.shou_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = list.get(position).id;
                bach.ba(position,list);
                list.get(position).whetherCollection =1;
                notifyDataSetChanged();
                addUserVideoCollection.reqeust(loginBean.getId(),loginBean.getSessionId(),id);
            }
        });


        if(list.get(position).whetherBuy == 1){
            holder.game_image1.setBackgroundResource(R.mipmap.common_icon_comment_samll_s);
            holder.miao_text.setVisibility(View.GONE);
        }
        if(list.get(position).whetherBuy == 2){
            holder.miao_text.setBackgroundResource(R.drawable.aa);
            holder.miao_text.getBackground().setAlpha(30);
            holder.miao_text.setText("试看15s,购买看完整视频");
            holder.game_image1.setBackgroundResource(R.mipmap.common_icon_toll_n);
        }
        holder.game_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).whetherBuy== 1){
                    View contentView = LayoutInflater.from(context).inflate(R.layout.popuplayout1, null);
                    String id = list.get(position).id;
                    edit_fa = contentView.findViewById(R.id.eidt_fa);
                    button_shu = contentView.findViewById(R.id.button_shu);
                    TextView  button_shu1 = contentView.findViewById(R.id.button_shu1);
                    edit_fa.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() > 0) {
                                button_shu.setVisibility(View.GONE);
                                button_shu1.setVisibility(View.VISIBLE);
                            } else {
                                button_shu.setVisibility(View.VISIBLE);
                                button_shu1.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    button_shu1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s = edit_fa.getText().toString();
                            addVideoComment = new AddVideoComment(new Backn());
                            addVideoComment.reqeust(loginBean.getId(),loginBean.getSessionId(),id, s);
                        }
                    });
                    mPopWindow1 = new PopupWindow(contentView,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            true);
                    mPopWindow1.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);

                }else if(list.get(position).whetherBuy == 2){
                    View contentView = LayoutInflater.from(context).inflate(R.layout.popuplayout, null);
                    TextView liji= contentView.findViewById(R.id.liji);
                    textView1 = contentView.findViewById(R.id.texto);
                    ImageView guan =contentView.findViewById(R.id.guan);
                    Button goumai = contentView.findViewById(R.id.goumai);
                    text_price = contentView.findViewById(R.id.text_price);
                    textView1.setText(list.get(position).price+"H币");
                    text_price.setText("我的H币: "+data1+", H币不足?");
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
                    liji.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context,"禁止充值",Toast.LENGTH_LONG).show();
                        }
                    });
                    goumai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String id = list.get(position).id;
                            int price = list.get(position).price;
                            View view1 =View.inflate(context,R.layout.pop,null);
                          TextView  textprice=  view1.findViewById(R.id.textView10);
                          textprice.setText("购买本视频将扣除"+price+"H币!");
                            dialog = new MyDialog(context, 200, 100, view1, R.style.dialog);
                            dialog.show();
                            final TextView cancel =
                                    (TextView) view1.findViewById(R.id.cancel);
                            final TextView confirm =
                                    (TextView)view1.findViewById(R.id.confirm);
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    videoBuy.reqeust(loginBean.getId(),loginBean.getSessionId(),id,price);
                                    dialog.dismiss();
                                    //设置whaybuy 2没有购买
                                    list.get(position).whetherBuy=1;
                                    notifyDataSetChanged();
                                }

                            });
                            findUserWallet1.reqeust(loginBean.getId(),loginBean.getSessionId());
                            text_price.setText("我的H币: "+data1+", H币不足?");
                            notifyDataSetChanged();
                        }
                    });

                }
            }

        });
    }
class Backh implements DataCall {

    @Override
    public void success(Object data, Object... args) {

    }

    @Override
    public void fail(ApiException data, Object... args) {

    }
}

    class Backg implements DataCall<Result> {

        @Override
        public void success(Result data, Object... args) {
            findUserWallet1.reqeust(loginBean.getId(),loginBean.getSessionId());

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backn implements DataCall {

        @Override
        public void success(Object data, Object... args) {
                    mPopWindow1.dismiss();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class Backv implements DataCall {

        @Override
        public void success(Object data, Object... args) {
            data1 = (double) data;


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
        private TextView miao_text;
        public VideoViewHolder(View rootView) {
            super(rootView);
            message = rootView.findViewById(R.id.message);
            mp_video = rootView.findViewById(R.id.mp_video);
            textView1 = rootView.findViewById(R.id.game_text1);
            textView2 = rootView.findViewById(R.id.game_text3);
            ren_text = rootView.findViewById(R.id.ren_text);
            shou_image = rootView.findViewById(R.id.shou_image);
            game_image1 = rootView.findViewById(R.id.game_image11);
            miao_text = rootView.findViewById(R.id.miao_text);
        }
    }
    public interface Bach{
        void ba(int i, List<GameBean> list);
    }
    public Bach bach;

    public void setBach(Bach bach) {
        this.bach = bach;
    }
    public interface Bacc{
        void bi(int i, List<GameBean> list);
    }
    public Bacc bacc;

    public void setBacc(Bacc bacc) {
        this.bacc = bacc;
    }

}

