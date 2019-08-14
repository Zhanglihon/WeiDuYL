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
import zhang.bw.com.common.bean.MyLishiBean;

public class MyLishiAdapter extends RecyclerView.Adapter<MyLishiAdapter.ViewHolder> {
    List<MyLishiBean> list = new ArrayList<>();
    Context context;

    public MyLishiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyLishiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =View.inflate(context,R.layout.li, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLishiAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageURI(list.get(position).imagePic);
        holder.li_text1.setText(list.get(position).doctorName);
        String format = "yyyy-MM-dd";
        long releaseTime = list.get(position).inquiryTime;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        holder.li_time.setText(formatter.format(new Date(releaseTime)));
        holder.li_text2.setText(list.get(position).jobTitle);
        holder.pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backf.bac(position,list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<MyLishiBean> data) {
        list.addAll(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView imageView;
        private TextView li_text1,li_text2,li_time,chakan,pingjia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.li_image);
            li_text1 = itemView.findViewById(R.id.li_text1);
            li_text2 = itemView.findViewById(R.id.li_text2);
            li_time = itemView.findViewById(R.id.li_time);
            chakan = itemView.findViewById(R.id.chakan);
            pingjia = itemView.findViewById(R.id.pingjia);

        }
    }
    public interface Backf{
        void bac(int i,List<MyLishiBean> list);
    }
    public Backf backf;

    public void setBackf(Backf backf) {
        this.backf = backf;
    }
}
