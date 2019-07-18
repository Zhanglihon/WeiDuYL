package fragment;

import android.annotation.SuppressLint;

import com.example.open_show.R;
import com.example.open_show.R2;

import java.util.List;

import adapter.MyAdapter1;
import adapter.MyAdapter2;
import adapter.MyBingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import zhang.bw.com.common.bean.BingBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDepartmentPresenter;
import zhang.bw.com.common.core.FindDiseaseCategory;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.exception.ApiException;
;
public class Fragment1 extends WDFragment {
    @BindView(R2.id.recyc1)
    RecyclerView recyc1;
    @BindView(R2.id.recyc2)
    RecyclerView recyc2;
    private FindDepartmentPresenter findDepartmentPresenter;
    private FindDiseaseCategory findDiseaseCategory;
    private MyAdapter2 myAdapter1;
    private MyBingAdapter myBingAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        findDepartmentPresenter = new FindDepartmentPresenter(new Backw());
        findDepartmentPresenter.reqeust();
        myAdapter1 = new MyAdapter2(getContext());
        recyc1.setAdapter(myAdapter1);
        myBingAdapter = new MyBingAdapter(getActivity());
        recyc2.setAdapter(myBingAdapter);
        findDiseaseCategory = new FindDiseaseCategory(new Backh());

        recyc2.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyc1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        myAdapter1.setBackf(new MyAdapter2.Backf() {
            @Override
            public void baf(int i, List<ShowBean> list) {
                int id = list.get(i).id;
                findDiseaseCategory = new FindDiseaseCategory(new Backh());
                findDiseaseCategory.reqeust(id+"");
               //嫑忘记刷新适配器
                myAdapter1.notifyDataSetChanged();

            }
        });
        myAdapter1.setOnRecyclerViewItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                myAdapter1.setThisPosition(position);
                myAdapter1.notifyDataSetChanged();
            }
            
        });

    }
    class Backw implements DataCall<List<ShowBean>> {

        @Override
        public void success(List<ShowBean> data, Object... args) {
            int id = data.get(0).id;
            findDiseaseCategory.reqeust(id+"");
            myAdapter1.addAll(data);
            myAdapter1.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backh implements DataCall<List<BingBean>>{

        @Override
        public void success(List<BingBean> data, Object... args) {
            myBingAdapter.addAll(data);
            myBingAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
