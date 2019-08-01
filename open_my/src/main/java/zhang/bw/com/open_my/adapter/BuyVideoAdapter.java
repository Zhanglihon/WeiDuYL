package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.jzvd.JZVideoPlayerStandard;
import zhang.bw.com.common.bean.BuyVideoBean;
import zhang.bw.com.common.util.DateUtils;
import zhang.bw.com.open_my.R;

import static zhang.bw.com.common.util.DateUtils.DATE_TIME_PATTERN;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class BuyVideoAdapter extends RecyclerView.Adapter<BuyVideoAdapter.holder> {
    Context context;
    List<BuyVideoBean>list;

    public BuyVideoAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.buyvideo_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        String url=list.get(position).original;
        try {
            holder.buyitem_time.setText(DateUtils.dateTransformer(list.get(position).createTime,DATE_TIME_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.buyitem_video_jzsp.setUp(url,JZVideoPlayerStandard.CURRENT_STATE_NORMAL,list.get(position).title);
        if(position==0){
            holder.buyitem_video_jzsp.startVideo();
        }
        Glide.with(context).load(list.get(position).original).into(holder.buyitem_video_jzsp.thumbImageView);
        holder.buyitem_video_jzsp.setUp(url,JZVideoPlayerStandard.CURRENT_STATE_NORMAL,list.get(position).title);
        holder.buyitem_video_jzsp.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 7播放比例,可以设置为16:9,4:3
        holder.buyitem_video_jzsp.widthRatio = 4;
        holder.buyitem_video_jzsp.heightRatio = 3;
        JZVideoPlayerStandard.releaseAllVideos();
        //设置全屏播放
        JZVideoPlayerStandard.FULLSCREEN_ORIENTATION=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;//横向
        JZVideoPlayerStandard.NORMAL_ORIENTATION=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//纵向
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<BuyVideoBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final JZVideoPlayerStandard buyitem_video_jzsp;
        private final TextView buyitem_time;
        private final TextView buyitem_sc;

        public holder(@NonNull View itemView) {
            super(itemView);
            buyitem_video_jzsp = itemView.findViewById(R.id.buyitem_video_jzsp);
            buyitem_time = itemView.findViewById(R.id.buyitem_time);
            buyitem_sc = itemView.findViewById(R.id.buyitem_sc);
        }
    }
}
