package fragment;

import android.annotation.SuppressLint;

import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
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

import static android.content.Context.MODE_PRIVATE;

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
    @BindView(R2.id.zhidao)
    ImageView zhidao;
    @BindView(R2.id.hua)
    TextView hua;
    @BindView(R2.id.wozji1)
    ImageView wozji1;
    private List<Barrage> mBarrages = new ArrayList<>();
    private FindVideoCommentList findVideoCommentList;
    @BindView(R2.id.image_three)
    ImageView image_three;
    private  int tag=0;
    private int id;
    public boolean aa =true;
    public  boolean bb = true;
    public boolean gg=true;
    private SharedPreferences pref1;
    //用于判断是否是第一次运行，运行后变为false
    private boolean isFirst = true;
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
        initB();

        three_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag == 0) {
                    recyc_thr.setVisibility(View.INVISIBLE);
                    tag = 1;
                } else {
                    recyc_thr.setVisibility(View.VISIBLE);
                    tag = 0;
                }
            }
        }
        );
        image_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mynameAdapter.setBackv(new MynameAdapter.Backv() {
           @Override
           public void bv(int i, List<NameBean> list) {
                id = list.get(i).id;
               findVideoVoList.reqeust(loginBean.getId(),loginBean.getSessionId(),id+"","1","10");

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

    private void initB() {
       pref1 =  getActivity().getSharedPreferences("logu", MODE_PRIVATE);
        isFirst = pref1.getBoolean("isFirstIn", true);//如果第一次运行，无isFirstIn值，自动获取第二个参数为默认值
        if (isFirst) {//如果为true，进入if语句
            hua.setVisibility(View.VISIBLE);
            zhidao.setVisibility(View.VISIBLE);
            wozji1.setVisibility(View.VISIBLE);
            zhidao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hua.setVisibility(View.GONE);
                    zhidao.setVisibility(View.GONE);
                    wozji1.setVisibility(View.GONE);
                }
            });
            SharedPreferences.Editor editor = pref1.edit();
            editor.putBoolean("isFirstIn", false);//保存isFirstIn值为false
            editor.commit();//提交数据
        } else {
            hua.setVisibility(View.GONE);
            zhidao.setVisibility(View.GONE);
            wozji1.setVisibility(View.GONE);
        }
    }

    class Backj implements DataCall<List<NameBean>>{

        @Override
        public void success(List<NameBean> data, Object... args) {
           id = data.get(0).id;
            findVideoVoList.reqeust(loginBean.getId(),loginBean.getSessionId(),id+"","1","10");
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
            findVideoCommentList.reqeust(id+"");
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
