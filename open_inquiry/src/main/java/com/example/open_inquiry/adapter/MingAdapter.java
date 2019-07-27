package com.example.open_inquiry.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.open_inquiry.R;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.ShowBean;
/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
public class MingAdapter extends RecyclerView.Adapter<MingAdapter.Holder> {
    List<ShowBean> list = new ArrayList<>();
    Context context;
    private int id;

    public MingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =View.inflate(context,R.layout.layout_mingadapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.text_ming .setText(list.get(i).getDepartmentName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.oncelicks(i,list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addAll(List<ShowBean> data) {
        list.addAll(data);
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView text_ming;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text_ming = itemView.findViewById(R.id.text_ming);
        }
    }


    public interface MyCallBack{
         void oncelicks(int i,List<ShowBean> list);
    }
    public MyCallBack myCallBack;

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }
}
