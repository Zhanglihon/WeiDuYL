package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.open_show.R;

import org.greenrobot.eventbus.EventBus;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fragment.Fragmentfore;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.LoginBean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/18
 * @Description：XXXX
 */
public class BingYouAdaoter extends RecyclerView.Adapter<BingYouAdaoter.Holder> {
    List<Byliebiao> list = new ArrayList<>();
    Context context;

    public BingYouAdaoter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_adapterbing,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        holder.text_titel.setText(list.get(i).getTitle());
        long releaseTime = list.get(i).getReleaseTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(releaseTime);
        holder.text_shijian.setText(dateString);
        holder.text_count.setText(list.get(i).getDetail());
        holder.text_shoucang.setText(list.get(i).getCollectionNum()+"");
        holder.text_jiayi.setText(list.get(i).getCommentNum()+"");
        int amount = list.get(i).getAmount();
        if(amount==0){
            holder.linearLayout.setVisibility(View.GONE);
        }else{
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.text_num.setText(amount+"");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoginBeanDao dao = DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME).getLoginBeanDao();
            List<LoginBean> loginBeans = dao.loadAll();

            if(loginBeans.size()!=0){
                Byliebiao byliebiao = list.get(i);
                EventBus.getDefault().post(new Fragmentfore());
                EventBus.getDefault().postSticky(byliebiao);
            }else{
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            }

        }
     });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addalter(List<Byliebiao> data) {
        if(!data.isEmpty()){
            list.clear();
        }
        list.addAll(data);
    }

    public void addalte(List<Byliebiao> data) {

        if(!data.isEmpty()){
            list.clear();
        }
        list.addAll(data);
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView text_titel,text_shijian,text_count,text_shoucang,text_jiayi,text_num;
        LinearLayout linearLayout;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text_titel=itemView.findViewById(R.id.text_titel);
            text_shijian=itemView.findViewById(R.id.text_shijian);
            text_count=itemView.findViewById(R.id.text_count);
            text_shoucang=itemView.findViewById(R.id.text_shoucang);
            text_jiayi=itemView.findViewById(R.id.text_jiayi);
            text_num=itemView.findViewById(R.id.text_num);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }

    }


}
