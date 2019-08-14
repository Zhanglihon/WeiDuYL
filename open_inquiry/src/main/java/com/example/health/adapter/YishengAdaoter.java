package com.example.health.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.open_inquiry.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.YishengBean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
public class YishengAdaoter extends RecyclerView.Adapter<YishengAdaoter.Holder> {
    List<YishengBean> list = new ArrayList<>();
    Context context;

    public YishengAdaoter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =View.inflate(context,R.layout.gg,null);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull Holder holder, final int position) {

                holder.imageView.setImageURI(list.get(position).imagePic);
                holder.textView.setText(list.get(position).doctorName);
                holder.textView.setBackgroundColor(list.get(position).textcolor);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <list.size() ; i++) {
                    list.get(i).textcolor=Color.parseColor("#999999");
                }
                list.get(position).textcolor=Color.parseColor("#3087ea");
                baop.bop(position,list);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void adALL(List<YishengBean> data) {
       if(!data.isEmpty()){
           list.clear();
       }
           list.addAll(data);


    }

    public class  Holder extends RecyclerView.ViewHolder{
        private SimpleDraweeView imageView;
        private TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.q1);
            textView = itemView.findViewById(R.id.qqt);
        }
    }
    public interface Baop{
        void bop(int i, List<YishengBean> list);
    }
    public Baop baop;

    public void setBaop(Baop baop) {
        this.baop = baop;
    }
}
