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
import zhang.bw.com.common.bean.DrugsSearchVoList;
import zhang.bw.com.common.bean.ShoucuoBean;

public class MyShouAdapter3 extends RecyclerView.Adapter<MyShouAdapter3.ViewHolder> {
    List<DrugsSearchVoList> list = new ArrayList<>();
    Context context;

    public MyShouAdapter3(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyShouAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context,R.layout.suo1,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyShouAdapter3.ViewHolder holder, int position) {
              holder.textView.setText(list.get(position).drugsName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll2(ShoucuoBean data) {
        list.addAll(data.drugsSearchVoList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView  textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_o1);
        }
    }
}
