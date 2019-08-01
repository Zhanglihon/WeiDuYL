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
import zhang.bw.com.common.bean.DiseaseSearchVoList;
import zhang.bw.com.common.bean.ShoucuoBean;

public class MyShouAdapter extends RecyclerView.Adapter<MyShouAdapter.ViewHolder> {
    List<DiseaseSearchVoList> list = new ArrayList<>();
    Context context;

    public MyShouAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyShouAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context,R.layout.suo,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyShouAdapter.ViewHolder holder, int position) {
              holder.textView.setText(list.get(position).diseaseName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addAll(ShoucuoBean data) {
        list.addAll(data.diseaseSearchVoList);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView  textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_o);
        }
    }
}
