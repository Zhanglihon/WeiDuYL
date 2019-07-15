package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.open_show.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.MyjiankangBean;

public class MyjikangAdapter extends RecyclerView.Adapter<MyjikangAdapter.ViewHolder>{
    List<MyjiankangBean> list = new ArrayList<>();
    Context context;

    public MyjikangAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyjikangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.jiankang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyjikangAdapter.ViewHolder holder, int position) {
        holder.textView1.setText(list.get(position).name);
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jianBack.jian(position,list);
//                   if(true){
//                       holder.textView1.setTextColor(Color.BLUE);
//                   }else {
//                       holder.textView1.setTextColor(Color.BLACK);
//                   }

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
            textView1 = itemView.findViewById(R.id.jian_text1);
        }
    }
    public interface JianBack{
        void jian(int i,List<MyjiankangBean> list);
    }
  public JianBack jianBack;

    public void setJianBack(JianBack jianBack) {
        this.jianBack = jianBack;
    }
}
