package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.ReBean;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.ViewHolder> {
    List<ReBean> list = new ArrayList<>();
    Context context;

    public ReAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =View.inflate(context,R.layout.re,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReAdapter.ViewHolder holder, int position) {
        holder.re_text.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addALL(List<ReBean> data) {
        list.addAll(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView re_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            re_text = itemView.findViewById(R.id.text_re);
        }
    }
}
