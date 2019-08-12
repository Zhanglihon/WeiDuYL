package zhang.bw.com.open_my.Flag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.SBingBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.adapter.BingAdapter;
import zhang.bw.com.open_my.presenter.BingPresenter;

public class Jiankan_bingYouFra extends Fragment {

    private BingAdapter bingAdapter;
    private LoginBean loginBean;
    private RecyclerView bing_recyclerview;
    private RelativeLayout bing_ysj;
    private RelativeLayout bing_wsj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jian_kan_bing, container, false);
        bing_recyclerview = view.findViewById(R.id.bing_recyclerview);
        bing_ysj = view.findViewById(R.id.bing_ysj);
        bing_wsj = view.findViewById(R.id.bing_wsj);
        return view;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginBean = DaoMaster.newDevSession(getContext(),LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        BingPresenter bingPresenter=new BingPresenter(new scbyq());
        //获取布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bing_recyclerview.setLayoutManager(layoutManager);
        //创建适配器
        bingAdapter = new BingAdapter(getContext());
        bing_recyclerview.setAdapter(bingAdapter);
        bingPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),1,5);
    }
    class scbyq implements DataCall<List<SBingBean>> {

        @Override
        public void success(List<SBingBean> data, Object... args) {
            if (data.size()==0){
                bing_ysj.setVisibility(View.GONE);
            }else {
                bing_wsj.setVisibility(View.GONE);
                bingAdapter.addAll(data);
                bingAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(),data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
