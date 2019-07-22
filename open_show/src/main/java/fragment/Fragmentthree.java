package fragment;

import android.annotation.SuppressLint;

import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.open_show.R;
import com.example.open_show.R2;

import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;

import java.util.ArrayList;
import java.util.List;
import adapter.MyAdapter2;
import adapter.MyadapterGame;
import adapter.MynameAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.GameBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.NameBean;
import zhang.bw.com.common.bean.PingBean;
import zhang.bw.com.common.core.AddUserVideoCollection;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindVideoCategoryList;
import zhang.bw.com.common.core.FindVideoCommentList;
import zhang.bw.com.common.core.FindVideoVoList;
import zhang.bw.com.common.core.WDFragment;
import zhang.bw.com.common.core.exception.ApiException;

public class Fragmentthree extends WDFragment {
    @BindView(R2.id.recyc_thr)
    RecyclerView recyc_thr;
    @BindView(R2.id.recyc_thr2)
    RecyclerView recyc_thr2;
    FindVideoCategoryList findVideoCategoryList;
    MynameAdapter mynameAdapter;
    FindVideoVoList findVideoVoList;
    LoginBean loginBean;
    private MyadapterGame myadapterGame;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    @BindView(R2.id.three_image)
    ImageView three_image;
    @BindView(R2.id.barrageView)
    BarrageView barrageView;
    private List<Barrage> mBarrages = new ArrayList<>();
    private FindVideoCommentList findVideoCommentList;
    private String id;
    public boolean aa =true;
    public  boolean bb = true;
    private AddUserVideoCollection addUserVideoCollection;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmentthree;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(getContext(),LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        findVideoCategoryList = new FindVideoCategoryList(new Backj());
        findVideoCategoryList.reqeust();
        mynameAdapter = new MynameAdapter(getContext());
        recyc_thr.setAdapter(mynameAdapter);
        recyc_thr.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        findVideoVoList = new FindVideoVoList(new Backo());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyc_thr2);
        recyc_thr2.setLayoutManager(layoutManager);
        myadapterGame = new MyadapterGame(getActivity());
        recyc_thr2.setAdapter(myadapterGame);
        mynameAdapter.setBackv(new MynameAdapter.Backv() {
           @Override
           public void bv(int i, List<NameBean> list) {
                id = list.get(i).id;
               findVideoVoList.reqeust(loginBean.getId(),loginBean.getSessionId(),id,"1","10");

           }
       });
       mynameAdapter.setOnRecyclerViewItemClickListener(new MyAdapter2.OnItemClickListener() {
           @Override
           public void onClick(int position) {
               mynameAdapter.setThisPosition(position);
               mynameAdapter.notifyDataSetChanged();
           }
       });
        recyc_thr2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(layoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                        if (viewHolder != null && viewHolder instanceof MyadapterGame.VideoViewHolder) {
                            ((MyadapterGame.VideoViewHolder) viewHolder).mp_video.startVideo();
                        }

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });

        findVideoCommentList = new FindVideoCommentList(new Backg());
        addUserVideoCollection = new AddUserVideoCollection(new Backd());
        myadapterGame.setBach(new MyadapterGame.Bach() {
            @Override
            public void ba(int i, List<GameBean> list) {
                addUserVideoCollection.reqeust(loginBean.getId(),loginBean.getSessionId(),id);
            }
        });
        myadapterGame.setBacc(new MyadapterGame.Bacc() {
            @Override
            public void bi(int i, List<GameBean> list) {
                id = list.get(i).id;
                findVideoCommentList.reqeust(id);
                if(aa){
                    aa= false;
                    barrageView.setVisibility(View.GONE);
                }else {
                    aa= true;
                    barrageView.setVisibility(View.VISIBLE);
                }
            }
        });
        barrageView.setBarrages(mBarrages);
    }
    class Backj implements DataCall<List<NameBean>>{

        @Override
        public void success(List<NameBean> data, Object... args) {
           id = data.get(0).id;
            findVideoVoList.reqeust(loginBean.getId(),loginBean.getSessionId(),id,"1","10");
            mynameAdapter.addAll(data);
            mynameAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class Backo implements DataCall<List<GameBean>>{
        @Override
        public void success(List<GameBean> data, Object... args) {
             id = data.get(0).id;
            findVideoCommentList.reqeust(id);
            myadapterGame.addAll(data);
            myadapterGame.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    class Backg implements DataCall<List<PingBean>>{

        @Override
        public void success(List<PingBean> data, Object... args) {

            for (int i = 0; i < data.size(); i++) {
                barrageView.addBarrage(new Barrage(data.get(i).content,R.color.colorhui));
            };

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backd implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }



}
