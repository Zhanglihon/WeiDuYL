package adapter;

import android.content.Context;
import android.graphics.Color;
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
import zhang.bw.com.common.bean.ShowBean;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    List<ShowBean> list = new ArrayList<>();
    Context context;
    public MyAdapter2(Context context) {
        this.context = context;
    }
    private OnItemClickListener onRecyclerViewItemClickListener;

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
    public MyAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =View.inflate(context,R.layout.adapter2,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).departmentName);
        if(getthisPosition() == position){

            holder.textView.setTextColor(Color.parseColor("#87B9F3"));
            holder.textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {
            holder.textView.setTextColor(Color.BLACK);
            holder.textView.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               backf.baf(position,list);
                onRecyclerViewItemClickListener.onClick(position);
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
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter2_text1);
        }
    }
    public interface Backf {
        void baf(int i,List<ShowBean> list);
    }
    public Backf backf;

    public void setBackf(Backf backf) {
        this.backf = backf;
    }
}
