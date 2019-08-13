package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.SBingBean;
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
public class BingAdapter extends RecyclerView.Adapter<BingAdapter.holder> {
    Context context;
    List<SBingBean>list;

    public BingAdapter(Context context) {
        this.context = context;
        list=new ArrayList();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.bing_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        holder.bing_item_title.setText(list.get(position).title);
        holder.bing_item_content.setText(list.get(position).disease);
        holder.bing_item_sc.setText(list.get(position).collectionNum+"");
        holder.bing_item_jy.setText(list.get(position).commentNum+"");
        try {
            holder.bing_item_minute.setText(DateUtils.dateTransformer(list.get(position).createTime,DATE_TIME_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bing_item_delete.setVisibility(View.GONE);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.bing_item_delete.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<SBingBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final TextView bing_item_title;
        private final TextView bing_item_content;
        private final ImageView bing_item_delete;
        private final TextView bing_item_sc;
        private final TextView bing_item_jy;
        private final TextView bing_item_minute;

        public holder(@NonNull View itemView) {
            super(itemView);
            bing_item_title = itemView.findViewById(R.id.bing_item_title);
            bing_item_content = itemView.findViewById(R.id.bing_item_content);
            bing_item_delete = itemView.findViewById(R.id.bing_item_delete);
            bing_item_sc = itemView.findViewById(R.id.bing_item_sc);
            bing_item_jy = itemView.findViewById(R.id.bing_item_jy);
            bing_item_minute = itemView.findViewById(R.id.bing_item_minute);
        }
    }
}
