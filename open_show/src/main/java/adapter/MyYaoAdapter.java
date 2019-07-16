package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.YaoBean;

public class MyYaoAdapter extends RecyclerView.Adapter<MyYaoAdapter.ViewHolder> {
    List<YaoBean> list = new ArrayList<>();
    Context context;

    public MyYaoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyYaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.yao,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyYaoAdapter.ViewHolder holder, int position) {
        String[] split = list.get(position).name.split("]");
        String s = split[1];
        Glide.with(context).load(list.get(position).picture).into(holder.imageView1);
//        if(){
//            holder.textView.setText(s);
//        }else {
            holder.textView.setText(list.get(position).name);
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addALL(List<YaoBean> data) {
        if(!data.isEmpty()){
            list.clear();
        }
        list.addAll(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.yao_image);
            textView = itemView.findViewById(R.id.yao_text1);
        }
    }
}
