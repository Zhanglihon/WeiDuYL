package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.WdplBean;
import zhang.bw.com.common.bean.WoDeplBean;
import zhang.bw.com.open_my.R;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class WdplAdapter extends RecyclerView.Adapter<WdplAdapter.holder> {
    Context context;
    List<WdplBean>list;

    public WdplAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.wdpl_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        WdplBean wdplBean=list.get(position);
        holder.wdpl_text_title.setText(wdplBean.nickNmae);
        holder.wdpl_text_content.setText(wdplBean.content);
        holder.wdpl_item_data.setText(wdplBean.commentTime+"");
        holder.wdpl_item_dznum.setText(wdplBean.supportNum);
        holder.wdpl_item_cainum.setText(wdplBean.opposeNum);
        Glide.with(context).load(wdplBean.headPic).apply(RequestOptions.circleCropTransform()).into(holder.wdpl_item_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(WoDeplBean data) {
        List<WdplBean> list1= data.otherSickCircleCommentList;
        list.addAll(list1);
    }


    public class holder extends RecyclerView.ViewHolder{

        private final ImageView wdpl_item_image;
        private final TextView wdpl_text_title;
        private final TextView wdpl_text_content;
        private final CheckBox wdpl_item_dz;
        private final TextView wdpl_item_dznum;
        private final CheckBox wdpl_item_cai;
        private final TextView wdpl_item_cainum;
        private final ImageView wdpl_item_doctor;
        private final TextView wdpl_item_data;
        private final CheckBox wdpl_item_caina;

        public holder(@NonNull View itemView) {
            super(itemView);
            wdpl_item_image = itemView.findViewById(R.id.wdpl_item_image);
            wdpl_text_title = itemView.findViewById(R.id.wdpl_text_title);
            wdpl_text_content = itemView.findViewById(R.id.wdpl_text_content);
            wdpl_item_dz = itemView.findViewById(R.id.wdpl_item_dz);
            wdpl_item_dznum = itemView.findViewById(R.id.wdpl_item_dznum);
            wdpl_item_cai = itemView.findViewById(R.id.wdpl_item_cai);
            wdpl_item_cainum = itemView.findViewById(R.id.wdpl_item_cainum);
            wdpl_item_doctor = itemView.findViewById(R.id.wdpl_item_doctor);
            wdpl_item_data = itemView.findViewById(R.id.wdpl_item_data);
            wdpl_item_caina = itemView.findViewById(R.id.wdpl_item_caina);
        }
    }
}
