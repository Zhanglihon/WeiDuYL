package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.util.Constant;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {
    List<ShowBean> list = new ArrayList<>();
    Context context;
    public MyAdapter1(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =View.inflate(context,R.layout.adapter1,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter1.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).pic).into(holder.imageView);
        holder.textView.setText(list.get(position).departmentName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ide = list.get(position).id;
                ShowBean showBean = list.get(position);
                ARouter.getInstance().build(Constant.ACTIVITY_URL_INSHOW).withInt("ide",ide).withString("list",list.toString()).navigation();
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.adapter_image1);
            textView = itemView.findViewById(R.id.adapter_text1);
        }
    }

}
