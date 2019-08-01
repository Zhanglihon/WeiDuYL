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
import zhang.bw.com.common.bean.DoctorSearchVoList;
import zhang.bw.com.common.bean.ShoucuoBean;

public class MyShouAdapter2 extends RecyclerView.Adapter<MyShouAdapter2.ViewHolder> {
    List<DoctorSearchVoList> list = new ArrayList<>();
    Context context;

    public MyShouAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyShouAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context,R.layout.suo2,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyShouAdapter2.ViewHolder holder, int position) {
              holder.textView.setText(list.get(position).doctorName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addAll1(ShoucuoBean data) {
        list.addAll(data.doctorSearchVoList);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView  textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_o2);
        }
    }
}
