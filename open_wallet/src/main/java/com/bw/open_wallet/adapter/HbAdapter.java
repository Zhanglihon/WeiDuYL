package com.bw.open_wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.open_wallet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.bean.XfjlBean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/15
 * @Description：XXXX
 */
public class HbAdapter extends RecyclerView.Adapter<HbAdapter.Holder> {
    List<XfjlBean> list;
    Context context;

    public HbAdapter(List<XfjlBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_hbadapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
            if (list.get(i).type==1){
                holder.text_content.setText("签到");
            }else if(list.get(i).type==2){
                holder.text_content.setText("病友圈首评");
            }else if(list.get(i).type==3){
                holder.text_content.setText("首发病友圈");
            }else if(list.get(i).type==4){
                holder.text_content.setText("完善档案");
            }else if(list.get(i).type==5){
                holder.text_content.setText("健康评测");
            }else if(list.get(i).type==6){
                holder.text_content.setText("悬赏消费");
            }else if(list.get(i).type==7){
                holder.text_content.setText("悬赏奖励");
            }else if(list.get(i).type==8){
                holder.text_content.setText("邀请奖励");
            }else if(list.get(i).type==9){
                holder.text_content.setText("问诊消费");
            }else if(list.get(i).type==10){
                holder.text_content.setText("问诊收入");
            }else if(list.get(i).type==11){
                holder.text_content.setText("观看资讯");
            }else if(list.get(i).type==12){
                holder.text_content.setText("送礼物");
            }else if(list.get(i).type==13){
                holder.text_content.setText("绑定身份证");
            }else if(list.get(i).type==14){
                holder.text_content.setText("绑定银行卡");
            }else if(list.get(i).type==15){
                holder.text_content.setText("充值");
            }else if(list.get(i).type==16){
                holder.text_content.setText("提现");
            }else if(list.get(i).type==17){
                holder.text_content.setText("购买健康视频");
            }
            long createTime = list.get(i).createTime;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(new Date(createTime));
            holder.text_createTime.setText(dateString);
            holder.text_money.setText(list.get(i).changeNum+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends  RecyclerView.ViewHolder {
        TextView text_createTime,text_content;
        private final TextView text_money;

        public Holder(@NonNull View itemView) {
            super(itemView);
            text_content=itemView.findViewById(R.id.text_content);
            text_createTime=itemView.findViewById(R.id.text_createTime);
            text_money = itemView.findViewById(R.id.text_money);
        }
    }
    public void setData(List<XfjlBean> lists){
        this.list.addAll(lists);
        notifyDataSetChanged();
    }
}
