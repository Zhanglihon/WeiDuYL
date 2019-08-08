package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.BingyouBean;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.activity.WdplActivity;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class BingYouAdapter extends RecyclerView.Adapter<BingYouAdapter.holder> {
    Context context;
    List<BingyouBean>list;

    public BingYouAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.bingyou_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int position) {
        holder.bingyou_item_title.setText(list.get(position).title);
        holder.bingyou_item_content.setText(list.get(position).detail);
        holder.bingyou_item_ckpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=list.get(position).sickCircleId;
                Log.i("aaa",id+"");
                Intent intent=new Intent(context,WdplActivity.class);
                intent.putExtra("id",id+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<BingyouBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final TextView bingyou_item_title;
        private final TextView bingyou_item_content;
        private final TextView bingyou_item_ckpl;

        public holder(@NonNull View itemView) {
            super(itemView);
            bingyou_item_title = itemView.findViewById(R.id.bingyou_item_title);
            bingyou_item_content = itemView.findViewById(R.id.bingyou_item_content);
            bingyou_item_ckpl = itemView.findViewById(R.id.bingyou_item_ckpl);
        }
    }
}
