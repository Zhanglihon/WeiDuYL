package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.WdgzBean;
import zhang.bw.com.open_my.R;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class WdgzAdapter extends RecyclerView.Adapter<WdgzAdapter.holder> {
    Context context;
    List<WdgzBean>list;

    public WdgzAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.wdgz_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.guangzhu_name.setText(list.get(position).name);
        holder.guanzhu_zhuren.setText(list.get(position).jobTitle);
        holder.guanzhu_dizhi.setText(list.get(position).inauguralHospital);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{

        private final ImageView guanzhu_iamgeview;
        private final TextView guangzhu_name;
        private final TextView guanzhu_zhuren;
        private final TextView guanzhu_dizhi;
        private final TextView haoping_shuliang;
        private final TextView guanzhu_hznum;
        private final TextView guanzhu_ks;

        public holder(@NonNull View itemView) {
            super(itemView);
            guanzhu_iamgeview = itemView.findViewById(R.id.guanzhu_iamgeview);
            guangzhu_name = itemView.findViewById(R.id.guangzhu_name);
            guanzhu_zhuren = itemView.findViewById(R.id.guanzhu_zhuren);
            guanzhu_dizhi = itemView.findViewById(R.id.guanzhu_dizhi);
            haoping_shuliang = itemView.findViewById(R.id.haoping_shuliang);
            guanzhu_hznum = itemView.findViewById(R.id.guanzhu_hznum);
            guanzhu_ks = itemView.findViewById(R.id.guanzhu_ks);
        }
    }
}
