package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.SuggestBean;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.activity.SuggestActivity;
import zhang.bw.com.open_my.activity.WdxxActivity;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.holder> {
    Context context;
    List<SuggestBean>list;

    public SuggestAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.suggest_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.suggest_item_name.setText(list.get(position).releaseUserNickName);
        holder.suggest_item_bl.setText(list.get(position).title);
        holder.suggest_item_bz.setText(list.get(position).disease);
        holder.suggest_item_time.setText(list.get(position).adoptTime+"");
        holder.suggest_item_content.setText(list.get(position).content);
        Glide.with(context).load(list.get(position).releaseUserHeadPic).apply(RequestOptions.circleCropTransform()).into(holder.suggest_item_tx);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{

        public final ImageView suggest_item_tx;
        private final TextView suggest_item_name;
        private final TextView suggest_item_bl;
        private final TextView suggest_item_bz;
        private final TextView suggest_item_time;
        private final TextView suggest_item_content;

        public holder(@NonNull View itemView) {
            super(itemView);
            suggest_item_tx = itemView.findViewById(R.id.suggest_item_tx);
            suggest_item_name = itemView.findViewById(R.id.suggest_item_name);
            suggest_item_bl = itemView.findViewById(R.id.suggest_item_bl);
            suggest_item_bz = itemView.findViewById(R.id.suggest_item_bz);
            suggest_item_time = itemView.findViewById(R.id.suggest_item_time);
            suggest_item_content = itemView.findViewById(R.id.suggest_item_content);
        }
    }
}
