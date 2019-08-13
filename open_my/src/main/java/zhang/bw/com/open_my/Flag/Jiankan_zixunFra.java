package zhang.bw.com.open_my.Flag;

import android.os.Bundle;
import android.util.Log;
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
import zhang.bw.com.common.bean.ShouziBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.adapter.ZixunItemAdapter;
import zhang.bw.com.open_my.presenter.ShouXixunPresenter;

public class Jiankan_zixunFra extends Fragment {


    private LoginBean loginBeanDao;
    RecyclerView recyclerView;
    private ZixunItemAdapter zixunItemAdapter;
    private RelativeLayout zixun_ysj;
    private RelativeLayout zixun_wsj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.jian_kan_zixun, container, false);
        recyclerView = inflate.findViewById(R.id.xixun_recy);
        zixun_ysj = inflate.findViewById(R.id.zixun_ysj);
        zixun_wsj = inflate.findViewById(R.id.zixun_wsj);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginBeanDao = DaoMaster.newDevSession(getContext(), LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        zixunItemAdapter = new ZixunItemAdapter(getContext());
        recyclerView.setAdapter(zixunItemAdapter);

        ShouXixunPresenter shouXixunPresenter = new ShouXixunPresenter(new Myxiun());
        shouXixunPresenter.reqeust(loginBeanDao.getId(), loginBeanDao.getSessionId(), 1 + "", 5 + "");
        Log.i("qw", "onActivityCreated: " + loginBeanDao.id + "----------" + loginBeanDao.sessionId);

    }

    class Myxiun implements DataCall<List<ShouziBean>> {
        @Override
        public void success(List<ShouziBean> data, Object... args) {
            if (data.size() == 0) {
                zixun_ysj.setVisibility(View.GONE);
            }else {
                zixun_wsj.setVisibility(View.GONE);
                zixunItemAdapter.addd(data);
                zixunItemAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
