package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.open_show.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.PingLunBean;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/22
 * @Description：XXXX
 */
public class PingLunlpAdapter extends RecyclerView.Adapter<PingLunlpAdapter.Holder> {
    Context context;
    List<PingLunBean>  list = new ArrayList<>();
    public PingLunlpAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_popupadapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(list.get(i).getHeadPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.image_view_tou);

        holder.text_name.setText(list.get(i).getNickName());
        holder.text_count.setText(list.get(i).getContent());
        long Time = list.get(i).getCommentTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String commentTime = formatter.format(Time);
        holder.text_time.setText(commentTime);
        holder.text_cai.setText(list.get(i).getOpinion()+"");
        holder.text_zan.setText(list.get(i).getSupportNum()+"");
        int whetherDoctor = list.get(i).getWhetherDoctor();
        Log.e("aaa",whetherDoctor+"");
        if(whetherDoctor==1){
            holder.image_yi.setVisibility(View.VISIBLE);
        }else{
            holder.image_yi.setVisibility(View.INVISIBLE);
        }
        int opinion = list.get(i).getOpinion();
        if(opinion==1){
            Glide.with(context).load(R.mipmap.dianzan_s).into(holder.image_zan);
        }else if(opinion==2){
            Glide.with(context).load(R.mipmap.diancai_s).into(holder.image_zan);
        }
        holder.image_cai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opposeNum = list.get(i).getOpposeNum();
                opposeNum++;
                holder.text_cai.setText(opposeNum+"");
                Glide.with(context).load(R.mipmap.diancai_s).into(holder.image_cai);
            }
        });
        holder.image_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int supportNum = list.get(i).getSupportNum();
                supportNum++;
                holder.text_zan.setText(supportNum+"");
                Glide.with(context).load(R.mipmap.dianzan_s).into(holder.image_zan);
            }
        });

        holder.image_view_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳入他的页面
                int commentUserId = list.get(i).getCommentUserId();
                String headPic = list.get(i).getHeadPic();
                ARouter.getInstance().build(Constant.ACTIVITY_URL_CHAFABIAO)
                        .withInt("commentUserId",commentUserId)
                        .withString("headPic",headPic)
                        .navigation();
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addalter(List<PingLunBean> data) {
        list.addAll(data);
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView text_zan,text_cai,text_name,text_count,text_time;
        ImageView image_zan,image_cai,image_view_tou,image_yi;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text_cai=itemView.findViewById(R.id.text_cai);
            text_zan=itemView.findViewById(R.id.text_zan);
            text_name=itemView.findViewById(R.id.text_name);
            text_count=itemView.findViewById(R.id.text_count);
            text_time=itemView.findViewById(R.id.text_time);
            image_zan=itemView.findViewById(R.id.image_zan);
            image_cai=itemView.findViewById(R.id.image_cai);
            image_view_tou=itemView.findViewById(R.id.image_view_tou);
            image_yi=itemView.findViewById(R.id.image_yi);
        }
    }
}
