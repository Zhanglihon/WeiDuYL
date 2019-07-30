package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.BingBean;

public class MingAdapter2 extends RecyclerView.Adapter<MingAdapter2.ViewHolder> {
    List<BingBean> list = new ArrayList<>();
    Context context;

    public MingAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MingAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.layout_mingadapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MingAdapter2.ViewHolder holder, int i ){
        holder.text_ming.setText(list.get(i).name);

        holder.text_ming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCallBack.oncelicks(list.get(i).name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<BingBean> data) {
        if(!data.isEmpty()){
            list.clear();
        }
        list.addAll(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_ming;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_ming = itemView.findViewById(R.id.text_ming);
        }
    }
    MyCallBack myCallBack;

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public interface MyCallBack{
        public void oncelicks(String name);
    }
}
