package com.example.open_inquiry.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private YishengAdaoter.OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

    }
    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
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
                if(thisPosition == position){
                    holder.textView.setBackgroundColor(Color.parseColor("#3087EA"));
             }else {
                    holder.textView.setBackgroundColor(Color.parseColor("#999999"));
                }
            holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baop.bop(position,list);
                onRecyclerViewItemClickListener.onClick(position);
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
        void bop(int i,List<YishengBean> list);
    }
    public Baop baop;

    public void setBaop(Baop baop) {
        this.baop = baop;
    }
}
