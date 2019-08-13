package zhang.bw.com.open_my.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.WdgzBean;
import zhang.bw.com.open_my.R;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class WdgzAdapter extends RecyclerView.Adapter<WdgzAdapter.holder> {
    Context context;
    List<WdgzBean>list;
    private float moveX;
    private float moveY;
    private float pressX;
    private float pressY;
    public WdgzAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.wdgz_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        holder.guangzhu_name.setText(list.get(position).name);
        holder.guanzhu_zhuren.setText(list.get(position).jobTitle);
        holder.guanzhu_dizhi.setText(list.get(position).inauguralHospital+"/");
        holder.guanzhu_ks.setText(list.get(position).departmentName);
        holder.haoping_shuliang.setText(list.get(position).praiseNum+"%");
        holder.guanzhu_hznum.setText(list.get(position).number+"");
        Glide.with(context).load(list.get(position).imagePic).into(holder.guanzhu_iamgeview);
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    //按下
                    case MotionEvent.ACTION_DOWN:
                        pressX = event.getX();
                        pressY = event.getY();
                        break;
                    //移动
                    case MotionEvent.ACTION_MOVE:
                        if (moveX-pressX>0&&Math.abs(moveY-pressY)<50){
                            holder.guanzhu_image_view_view.setVisibility(View.GONE);
                        }else if (moveX - pressX < 0 && Math.abs(moveY - pressY) < 50){
                            holder.guanzhu_image_view_view.setVisibility(View.VISIBLE);
                        }
                        moveX = event.getX();
                        moveY = event.getY();
                        break;
                    //松开
                    case MotionEvent.ACTION_UP:
                        if (moveX-pressX > 0 && Math.abs(moveY - pressY) < 50) {
                            //Log.i("message", "向右");
                        } else if (moveX - pressX < 0 && Math.abs(moveY - pressY) < 50) {
                            //Log.i("message", "向左");
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<WdgzBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final ImageView guanzhu_iamgeview;
        private final TextView guangzhu_name;
        private final TextView guanzhu_zhuren;
        private final TextView guanzhu_dizhi;
        private final TextView haoping_shuliang;
        private final TextView guanzhu_hznum;
        private final TextView guanzhu_ks;
        private final TextView guanzhu_image_view_view;

        public holder(@NonNull View itemView) {
            super(itemView);
            guanzhu_iamgeview = itemView.findViewById(R.id.guanzhu_iamgeview);
            guangzhu_name = itemView.findViewById(R.id.guangzhu_name);
            guanzhu_zhuren = itemView.findViewById(R.id.guanzhu_zhuren);
            guanzhu_dizhi = itemView.findViewById(R.id.guanzhu_dizhi);
            haoping_shuliang = itemView.findViewById(R.id.haoping_shuliang);
            guanzhu_hznum = itemView.findViewById(R.id.guanzhu_hznum);
            guanzhu_ks = itemView.findViewById(R.id.guanzhu_ks);
            guanzhu_image_view_view = itemView.findViewById(R.id.guanzhu_image_view_view);
        }
    }
}
