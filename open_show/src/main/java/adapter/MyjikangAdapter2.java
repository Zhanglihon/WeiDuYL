package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.MyjiankangBean;

public class MyjikangAdapter2 extends RecyclerView.Adapter<MyjikangAdapter2.ViewHolder>{
    List<MyjiankangBean> list = new ArrayList<>();
    Context context;
    private MyAdapter2.OnItemClickListener onRecyclerViewItemClickListener;

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

    public void setOnRecyclerViewItemClickListener(MyAdapter2.OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    public MyjikangAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.jiankangq,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView1.setText(list.get(position).name);
        if(getthisPosition() == position){
            holder.textView1.setTextColor(Color.parseColor("#87B9F3"));
            holder.textView1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {
            holder.textView1.setTextColor(Color.BLACK);
            holder.textView1.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backl.bal(position,list);
                onRecyclerViewItemClickListener.onClick(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addALL(List<MyjiankangBean> data) {
        list.addAll(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.q_text1);
        }
    }
    public interface Backl{
        void bal(int i, List<MyjiankangBean> list);
    }
    public Backl backl;

    public void setBackl(Backl backl) {
        this.backl = backl;
    }
}
