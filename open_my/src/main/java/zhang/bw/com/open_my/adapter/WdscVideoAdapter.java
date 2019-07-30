package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.DatabaseUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.jzvd.JZVideoPlayerStandard;
import zhang.bw.com.common.bean.WdscVideoBean;
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
public class WdscVideoAdapter extends RecyclerView.Adapter<WdscVideoAdapter.holder> {
    Context context;
    List<WdscVideoBean>list;

    public WdscVideoAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.wdscvideo_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        String url=list.get(position).shearUrl;
        holder.wdscvideo_buynum.setText(list.get(position).buyNum+"人已购买");
        try {
            holder.wdscvideo_time.setText(DateUtils.dateTransformer(list.get(position).createTime,DATE_TIME_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.wdsc_video_jzsp.setUp(url,JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        if(position==0){
            holder.wdsc_video_jzsp.startVideo();
        }
        Glide.with(context).load(list.get(position).shearUrl).into(holder.wdsc_video_jzsp.thumbImageView);
        holder.wdsc_video_jzsp.setUp(url,JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        holder.wdsc_video_jzsp.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 7播放比例,可以设置为16:9,4:3
        holder.wdsc_video_jzsp.widthRatio = 4;
        holder.wdsc_video_jzsp.heightRatio = 3;
        JZVideoPlayerStandard.releaseAllVideos();
        //设置全屏播放
        JZVideoPlayerStandard.FULLSCREEN_ORIENTATION=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;//横向
        JZVideoPlayerStandard.NORMAL_ORIENTATION=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//纵向
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<WdscVideoBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final JZVideoPlayerStandard wdsc_video_jzsp;
        private final TextView wdscvideo_buynum;
        private final TextView wdscvideo_time;
        private final TextView wdscvideo_sc;

        public holder(@NonNull View itemView) {
            super(itemView);
            wdsc_video_jzsp = itemView.findViewById(R.id.wdsc_video_jzsp);
            wdscvideo_buynum = itemView.findViewById(R.id.wdscvideo_buynum);
            wdscvideo_time = itemView.findViewById(R.id.wdscvideo_time);
            wdscvideo_sc = itemView.findViewById(R.id.wdscvideo_sc);
        }
    }
}
