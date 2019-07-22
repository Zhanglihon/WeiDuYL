package com.example.open_inquiry.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.open_inquiry.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.bean.ShowBeans;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
public class MingAdapter extends RecyclerView.Adapter<MingAdapter.Holder> {
    List<ShowBean> list ;
    Context context;

    public MingAdapter(List<ShowBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mingadapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.text_ming .setText(list.get(i).getDepartmentName());
        holder.text_ming.setTextColor(list.get(i).textcolor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <list.size() ; i++) {
                    list.get(i).textcolor=Color.BLACK;
                }
                list.get(i).textcolor=Color.parseColor("#3087ea");
                notifyDataSetChanged();
                myCallBack.oncelicks(list.get(i).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setsssss(List<ShowBean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView text_ming;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text_ming = itemView.findViewById(R.id.text_ming);
        }
    }
    MyCallBack myCallBack;

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public interface MyCallBack{
        public void oncelicks(int id);
    }
}