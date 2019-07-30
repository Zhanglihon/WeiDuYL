package com.bw.open_wallet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.open_wallet.R;
import com.bw.open_wallet.RecordActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.HbchaXun;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/15
 * @Description：XXXX
 */
public class HbAdapter extends RecyclerView.Adapter<HbAdapter.Holder> {
    List<HbchaXun> list;
    Context context;

    public HbAdapter(List<HbchaXun> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_hbadapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
            holder.text_content.setText(list.get(i).getContent());
        Log.e("aaa",list.get(i).getCreateTime());
            holder.text_createTime.setText(list.get(i).getCreateTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends  RecyclerView.ViewHolder {
        TextView text_createTime,text_content;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text_content=itemView.findViewById(R.id.text_content);
            text_createTime=itemView.findViewById(R.id.text_createTime);
        }
    }
    public void setData(List<HbchaXun> lists){
        this.list.addAll(lists);
        notifyDataSetChanged();
    }
}
