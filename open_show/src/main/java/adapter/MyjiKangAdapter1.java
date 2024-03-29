package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.open_show.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.JanBean;

public class MyjiKangAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<JanBean> list = new ArrayList<>();
    Context context;

    public MyjiKangAdapter1(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        String[] split = list.get(position).thumbnail.split(";");
        if(split.length == 1){
            return 1;
        }else if(split.length == 3){
            return 3;
        }
        return 5;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType == 1){
           View view =LayoutInflater.from(context).inflate(R.layout.jiankang3,parent,false);
           return new PullImageHolder(view);
       }else if(viewType == 3){
           View view =LayoutInflater.from(context).inflate(R.layout.jiankang1,parent,false);
           return new RightImageHolder(view);
       }else {
           View view =LayoutInflater.from(context).inflate(R.layout.jiankang2,parent,false);
           return new ThreeImageHolder(view);
       }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  PullImageHolder){
            PullImageHolder holder1 = (PullImageHolder) holder;
            holder1.textView1.setText(list.get(position).title);
            holder1.name2.setText(list.get(position).source);
            String format = "yyyy-MM-dd";
            long releaseTime = list.get(position).releaseTime;
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            holder1.item1.setText(formatter.format(new Date(releaseTime)));
            Glide.with(context).load(list.get(position).thumbnail).into(holder1.imageView1);
        }else if(holder instanceof  RightImageHolder){
            String[] split = list.get(position).thumbnail.split(";");
            RightImageHolder holder2  = (RightImageHolder) holder;
            holder2.jian1_text1.setText(list.get(position).title);
            holder2.jian1_text2.setText(list.get(position).source);
            String format = "yyyy-MM-dd";
            long releaseTime = list.get(position).releaseTime;
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            holder2.jian1_item.setText(formatter.format(new Date(releaseTime)));
            ((RightImageHolder) holder).jian1_image1.setImageURI(split[0]);
            ((RightImageHolder) holder).jian1_image2.setImageURI(split[1]);
            ((RightImageHolder) holder).jian1_image3.setImageURI(split[2]);

        }else if(holder instanceof ThreeImageHolder){
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(backg!=null){
                   backg.bag(position,list);
               }
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addALL(List<JanBean> data) {
       if(!data.isEmpty()){
           list.clear();
       }
        list.addAll(data);
    }

    public void clear() {
        list.clear();
    }

    public class PullImageHolder  extends RecyclerView.ViewHolder{
        private TextView textView1,name2,item1;
        private SimpleDraweeView imageView1;
        public PullImageHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.jian3_text);
            name2 = itemView.findViewById(R.id.jian3_name);
            imageView1 = itemView.findViewById(R.id.jian3_image);
            item1 = itemView.findViewById(R.id.jian_item);

        }
    }
    private class RightImageHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView jian1_image1,jian1_image2,jian1_image3;
        private TextView jian1_text1,jian1_text2,jian1_item;
        public RightImageHolder(View itemView) {
            super(itemView);
            jian1_image1= itemView.findViewById(R.id.jian1_image1);
            jian1_image2 = itemView.findViewById(R.id.jian1_image2);
            jian1_image3 = itemView.findViewById(R.id.jian1_image3);
            jian1_text1 = itemView.findViewById(R.id.jian1_text);
            jian1_text2 = itemView.findViewById(R.id.jian1_text2);
            jian1_item = itemView.findViewById(R.id.jian1_item);
        }
    }

    private class ThreeImageHolder extends RecyclerView.ViewHolder {
        private TextView jian2_text1,jian3_text1;
        private ImageView jian2_image1,jian2_image2,jian3_image3;
        public ThreeImageHolder(View itemView) {
            super(itemView);
//            jian2_text1 = itemView.findViewById(R.id.jian2_text);
//            jian3_text1 = itemView.findViewById(R.id.jian3_text1);
//            jian2_image1 = itemView.findViewById(R.id.jian2_image1);
//            jian2_image2 = itemView.findViewById(R.id.jian2_image2);
//            jian3_image3 = itemView.findViewById(R.id.jian2_image3);
        }
    }
    public interface Backg{
        void bag(int i, List<JanBean> list);
    }
    public Backg backg;

    public void setBackg(Backg backg) {
        this.backg = backg;
    }
}
