package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.NameBean;

public class MynameAdapter extends RecyclerView.Adapter<MynameAdapter.ViewHolder> {
    List<NameBean> list = new ArrayList<>();
    Context context;
    private LinearLayout linearLayout;
    public MynameAdapter(Context context) {
        this.context = context;
    }
    private MyAdapter2.OnItemClickListener onRecyclerViewItemClickListener;

    public void clear() {
        list.clear();
    }

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


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =LayoutInflater.from(context).inflate(R.layout.uu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.uu_text1.setText(list.get(position).name);
        if(getthisPosition() == position){
            holder.uu_text1.setTextColor(Color.parseColor("#87B9F3"));
        }else {
            holder.uu_text1.setTextColor(Color.BLACK);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backv.bv(position,list);
                onRecyclerViewItemClickListener.onClick(position);
            }
        });



    }
    public void aa(){
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        if (list.size()==0){
            //隐藏item要把高度宽度设为0；
            linearLayout.setVisibility(View.GONE);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }else {
            //显示item要重新设置高度宽度；
            linearLayout.setVisibility(View.VISIBLE);
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        linearLayout.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<NameBean> data) {
        list.addAll(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView uu_text1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            uu_text1 = itemView.findViewById(R.id.uu_text1);
        }
    }
    public interface Backv{
        void bv(int i, List<NameBean> list);
    }
    public Backv backv;

    public void setBackv(Backv backv) {
        this.backv = backv;
    }

}
