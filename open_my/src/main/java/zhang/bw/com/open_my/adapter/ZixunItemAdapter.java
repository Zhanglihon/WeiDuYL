package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.ShouziBean;
import zhang.bw.com.common.util.DateUtils;
import zhang.bw.com.open_my.R;


/**
 * @Author：刘京源
 * @E-mail： 1179348728@qq.com
 * @Date： 2019/7/13 11:18
 * @Description：描述信息 下方的多条目
 */
public class ZixunItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

     Context context;
     private List<ShouziBean> list=new ArrayList<>();


    public ZixunItemAdapter(Context context) {
        this.context = context;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
            View view=LayoutInflater.from(context).inflate(R.layout.jiankang_item_one,null);
            return new ViewHolde1(view);
        }else if (viewType==3){
            View view=LayoutInflater.from(context).inflate(R.layout.jiankang_item_two,null);
              return new ViewHolde3(view);
        }else {
            View view=LayoutInflater.from(context).inflate(R.layout.jiankang_item_zeero,null);
            return new ViewHolde(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolde) {
            ShouziBean jianKangItemBean = list.get(position);
            ((ViewHolde) holder).name.setText(jianKangItemBean.title);
            //设置时间
        try {
            ((ViewHolde) holder).time_adapter.setText(DateUtils.dateTransformer(jianKangItemBean.createTime,DateUtils.DATE_TIME_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        }else if (holder instanceof  ViewHolde1){
            ShouziBean jianKangItemBean = list.get(position);
            ((ViewHolde1) holder).name.setText(jianKangItemBean.title);
          //  ((ViewHolde1) holder).nick.setText(jianKangItemBean.source);
            //设置时间
            try {
                ((ViewHolde1) holder).time_adapter.setText(DateUtils.dateTransformer(jianKangItemBean.createTime,DateUtils.DATE_TIME_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //一张图
            Glide.with(context)
                    .load(jianKangItemBean.thumbnail)
                    .apply(RequestOptions.centerCropTransform())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ViewHolde1) holder).img_one);
        }else if (holder instanceof ViewHolde3){
            ShouziBean jianKangItemBean = list.get(position);
            ((ViewHolde3) holder).name.setText(jianKangItemBean.title);
           // ((ViewHolde3) holder).nick.setText(jianKangItemBean.source);
            //设置时间
           try {
                ((ViewHolde3) holder).time_adapter.setText(DateUtils.dateTransformer(jianKangItemBean.createTime,DateUtils.DATE_TIME_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //三张图
            Glide.with(context)
                    .load(jianKangItemBean.thumbnail)
                    .apply(RequestOptions.centerCropTransform())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ViewHolde3) holder).img_one);
            Glide.with(context)
                    .load(jianKangItemBean.thumbnail)
                    .apply(RequestOptions.centerCropTransform())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ViewHolde3) holder).img_two);
            Glide.with(context)
                    .load(jianKangItemBean.thumbnail)
                    .apply(RequestOptions.centerCropTransform())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ViewHolde3) holder).img_three);
        }
/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oneClickIdListener != null){
                    oneClickIdListener.oneClick(list.get(position).id);
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addd(List<ShouziBean> data) {
        list.addAll(data);
    }


    public interface oneClickIdListener{
        void oneClick(int id);
    }

    private oneClickIdListener oneClickIdListener;
    public void setOneClickIdListener(ZixunItemAdapter.oneClickIdListener oneClickIdListener) {
        this.oneClickIdListener = oneClickIdListener;
    }


    public void clear() {
        if (list!=null){
            list.clear();
        }
    }

    @Override
    public int getItemViewType(int position) {

        String[] split = list.get(position).thumbnail.split(";");
        if (split.length == 1) {
            return 1;
        } else if (split.length >= 3) {
            return 3;
        } else {
            return 0;
        }
    }



class ViewHolde extends RecyclerView.ViewHolder{

    TextView name,nick,time_adapter;
    public ViewHolde(@NonNull View itemView) {

        super(itemView);

        name=itemView.findViewById(R.id.name);
        nick=itemView.findViewById(R.id.nick);
        time_adapter=itemView.findViewById(R.id.time_adapter);
    }
}
    class ViewHolde1 extends XRecyclerView.ViewHolder{

        TextView name,nick,time_adapter;
        ImageView img_one;
        public ViewHolde1(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            nick=itemView.findViewById(R.id.nick);
            time_adapter=itemView.findViewById(R.id.time_adapter);
            img_one=itemView.findViewById(R.id.img_one);
        }
    }

    class ViewHolde3 extends XRecyclerView.ViewHolder{
        TextView name,nick,time_adapter;
        ImageView img_one,img_two,img_three;
        public ViewHolde3(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            nick=itemView.findViewById(R.id.nick);
            time_adapter=itemView.findViewById(R.id.time_adapter);
            img_one=itemView.findViewById(R.id.img_one);
            img_two=itemView.findViewById(R.id.img_two);
            img_three=itemView.findViewById(R.id.img_three);
        }
    }



}
