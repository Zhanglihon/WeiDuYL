package com.example.health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.open_inquiry.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.MommentList;

public class PingAdapter extends RecyclerView.Adapter<PingAdapter.ViewHolder> {
    List<MommentList> list = new ArrayList<>();
    Context context;
    private long l;
    private Date parse;

    public PingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.ping,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PingAdapter.ViewHolder holder, int position) {
        holder.sl1.setImageURI(list.get(position).headPic);
        holder.mingzi.setText(list.get(position).nickName);
        holder.pinglun.setText(list.get(position).content);
        String  format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        holder.shijian.setText(sdf.format(new Date(list.get(position).commentTime)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<MommentList> commentList) {
        list.addAll(commentList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView sl1;
        private TextView mingzi;
        private TextView shijian;
        private TextView pinglun;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sl1 = itemView.findViewById(R.id.sl1);
            mingzi = itemView.findViewById(R.id.mingzi);
            shijian = itemView.findViewById(R.id.shijian);
            pinglun = itemView.findViewById(R.id.pinglun);
        }
    }
}
