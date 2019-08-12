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
import zhang.bw.com.common.bean.WdscVideoBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.adapter.WdscVideoAdapter;
import zhang.bw.com.open_my.presenter.WdscVideoPresenter;

public class Jiankan_ShipingFra extends Fragment {

    private LoginBean loginBean;
    private RecyclerView wdsc_recycler_view;
    private WdscVideoAdapter wdscVideoAdapter;
    private RelativeLayout shi_wsj;
    private RelativeLayout shi_ysj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jian_kan_shi, container, false);
        loginBean = DaoMaster.newDevSession(getContext(),LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        wdsc_recycler_view = view.findViewById(R.id.wdsc_Recycler_view);
        shi_wsj = view.findViewById(R.id.shi_wsj);
        shi_ysj = view.findViewById(R.id.shi_ysj);
        return view;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wdsc_recycler_view.setLayoutManager(layoutManager);
        //创建适配器
        wdscVideoAdapter = new WdscVideoAdapter(getContext());
        wdsc_recycler_view.setAdapter(wdscVideoAdapter);
        WdscVideoPresenter wdscVideoPresenter=new WdscVideoPresenter(new myvideo());
        wdscVideoPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),1,5);
    }
    class myvideo implements DataCall<List<WdscVideoBean>>{

        @Override
        public void success(List<WdscVideoBean> data, Object... args) {
            if (data.size()==0){
                shi_ysj.setVisibility(View.GONE);
            }else {
                shi_wsj.setVisibility(View.GONE);
                wdscVideoAdapter.addAll(data);
                wdscVideoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
