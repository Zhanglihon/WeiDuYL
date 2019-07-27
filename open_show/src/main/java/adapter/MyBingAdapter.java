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

public class MyBingAdapter extends RecyclerView.Adapter<MyBingAdapter.ViewHolder> {
    List<BingBean> list = new ArrayList<>();
    Context context;

    public MyBingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyBingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.bing,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBingAdapter.ViewHolder holder, int position) {
        holder.textView1.setText(list.get(position).name);
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backy.bay(position,list);
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
        private TextView textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.bing_text1);
        }
    }
    public interface Backy{
        void bay(int i,List<BingBean> list);
    }
    public Backy backy;

    public void setBacky(Backy backy) {
        this.backy = backy;
    }
}
