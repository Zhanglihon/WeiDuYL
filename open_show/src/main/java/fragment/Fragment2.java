package fragment;

import android.annotation.SuppressLint;

import com.example.open_show.R;
import com.example.open_show.R2;

import java.util.List;

import adapter.MyAdapter2;
import adapter.MyYaoAdapter;
import adapter.MyjikangAdapter;
import adapter.MyjikangAdapter2;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.YaoBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDrugsKnowledgeList;
import zhang.bw.com.common.core.FindInformationPlateList;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.exception.ApiException;

public class Fragment2 extends WDFragment {
    @BindView(R2.id.recyc3)
    RecyclerView recyc3;
    @BindView(R2.id.recyc4)
    RecyclerView recyc4;
    private FindInformationPlateList findInformationPlateList;
    private MyjikangAdapter2 myjikangAdapter;
    private FindDrugsKnowledgeList findDrugsKnowledgeList;
    private MyYaoAdapter myYaoAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment2;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        findInformationPlateList = new FindInformationPlateList(new Backr());
        findInformationPlateList.reqeust();
        myjikangAdapter = new MyjikangAdapter2(getContext());
        recyc3.setAdapter(myjikangAdapter);
        recyc3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        findDrugsKnowledgeList = new FindDrugsKnowledgeList(new Backu());
        myYaoAdapter = new MyYaoAdapter(getContext());
        recyc4.setAdapter(myYaoAdapter);
        recyc4.setLayoutManager(new GridLayoutManager(getContext(),3));
        myjikangAdapter.setOnRecyclerViewItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                myjikangAdapter.setThisPosition(position);
                myjikangAdapter.notifyDataSetChanged();
            }
        });
        myjikangAdapter.setBackl(new MyjikangAdapter2.Backl() {
            @Override
            public void bal(int i, List<MyjiankangBean> list) {
                String id = list.get(i).id;
                findDrugsKnowledgeList.reqeust(id,"1","10");
            }
        });

    }
    class Backr implements DataCall<List<MyjiankangBean>>{

        @Override
        public void success(List<MyjiankangBean> data, Object... args) {
               findDrugsKnowledgeList.reqeust(data.get(0).id,"1","10");
                myjikangAdapter.addALL(data);
                myjikangAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backu implements DataCall<List<YaoBean>>{
        @Override
        public void success(List<YaoBean> data, Object... args) {
            myYaoAdapter.addALL(data);
            myYaoAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
