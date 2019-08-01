package com.example.open_show;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import adapter.BaseRecycleAdapter;
import adapter.MyShouAdapter;
import adapter.MyShouAdapter2;
import adapter.MyShouAdapter3;
import adapter.ReAdapter;
import adapter.SeachRecordAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.bean.ReBean;
import zhang.bw.com.common.bean.ShoucuoBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.HomePageSearch;
import zhang.bw.com.common.core.PopularSearch;
import zhang.bw.com.common.core.exception.ApiException;

public class ShoucuoActivity extends AppCompatActivity {
    @BindView(R2.id.shou_text1)
    TextView shou_text1;
    @BindView(R2.id.edit1)
    EditText edit1;
    private HomePageSearch homePageSearch;
    MyShouAdapter myShouAdapter;
    MyShouAdapter2 myShouAdapter2;
    MyShouAdapter3 myShouAdapter3;
    @BindView(R2.id.shou_recyc)
    RecyclerView shou_recyc;
    private String s;
    @BindView(R2.id.imagejj)
    ImageView imagejj;
    @BindView(R2.id.text_xian)
    TextView text_xian;
    @BindView(R2.id.oo)
    TextView oo;
    private DbDao mDbDao;
    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private SeachRecordAdapter mAdapter;
    @BindView(R2.id.texth)
    TextView texth;
    @BindView(R2.id.shou_recyc1)
    RecyclerView shou_recyc1;
    private PopularSearch popularSearch;
    private ReAdapter reAdapter;
    @SuppressLint("WrongConstant")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucuo);
         ButterKnife.bind(this);
        mDbDao =new DbDao(this);
         homePageSearch = new HomePageSearch(new Backc());
         popularSearch = new PopularSearch(new Backv());
         popularSearch.reqeust();
         reAdapter = new ReAdapter(ShoucuoActivity.this);
         shou_recyc1.setAdapter(reAdapter);
         shou_recyc1.setLayoutManager(new GridLayoutManager(ShoucuoActivity.this,4));
        shou_recyc.setLayoutManager(new LinearLayoutManager(ShoucuoActivity.this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =new SeachRecordAdapter(mDbDao.queryData(""),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ShoucuoActivity.this,LinearLayoutManager.VERTICAL,false));
        initChildViews();
    }
    private void initChildViews() {

        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
                mDbDao.delete(mDbDao.queryData("").get(position));

                mAdapter.updata(mDbDao.queryData(""));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        shou_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit1.getText().toString().trim().length() != 0){
                    s = edit1.getText().toString();
                    homePageSearch.reqeust(s);
                    boolean hasData = mDbDao.hasData(edit1.getText().toString().trim());
                    if (!hasData){
                        mDbDao.insertData(edit1.getText().toString().trim());
                    }
                    //
                    mAdapter.updata(mDbDao.queryData(""));

                }

            }
        });
    }
     class Backv implements DataCall<List<ReBean>> {


         @Override
         public void success(List<ReBean> data, Object... args) {
             reAdapter.addALL(data);
             reAdapter.notifyDataSetChanged();
         }

         @Override
         public void fail(ApiException data, Object... args) {

         }
     }
    class Backc implements DataCall<ShoucuoBean> {
        @SuppressLint("WrongConstant")
        @Override
        public void success(ShoucuoBean data, Object... args) {
          if(s.equals("病")){
                         texth.setVisibility(View.GONE);
                        shou_recyc.setVisibility(View.VISIBLE);
                        imagejj.setVisibility(View.GONE);
                        text_xian.setVisibility(View.GONE);
                       shou_recyc1.setVisibility(View.GONE);
                        oo.setVisibility(View.GONE);
                        myShouAdapter = new MyShouAdapter(ShoucuoActivity.this);
                        shou_recyc.setAdapter(myShouAdapter);
                        myShouAdapter.addAll(data);
                        myShouAdapter.notifyDataSetChanged();
                    }else if(s.equals("医")){
                       texth.setVisibility(View.GONE);
                       shou_recyc.setVisibility(View.VISIBLE);
                       imagejj.setVisibility(View.GONE);
                       shou_recyc1.setVisibility(View.GONE);
                       oo.setVisibility(View.GONE);
                       text_xian.setVisibility(View.GONE);
                        myShouAdapter2 = new MyShouAdapter2(ShoucuoActivity.this);
                        shou_recyc.setAdapter(myShouAdapter2);
                        myShouAdapter2.addAll1(data);
                        myShouAdapter2.notifyDataSetChanged();

                    }else  if(s.equals("药")){
                      texth.setVisibility(View.GONE);
                       shou_recyc.setVisibility(View.VISIBLE);
                        imagejj.setVisibility(View.GONE);
                       shou_recyc1.setVisibility(View.GONE);
                        text_xian.setVisibility(View.GONE);
                        oo.setVisibility(View.GONE);
                        myShouAdapter3 = new MyShouAdapter3(ShoucuoActivity.this);
                        shou_recyc.setAdapter(myShouAdapter3);
                        myShouAdapter3.addAll2(data);
                        myShouAdapter3.notifyDataSetChanged();
                    }else {
              text_xian.setText("没有抱歉!没有找到 "+s+" 相关信息");
              mRecyclerView.setVisibility(View.GONE);
              texth.setVisibility(View.GONE);
              shou_recyc.setVisibility(View.GONE);
              shou_recyc1.setVisibility(View.GONE);
              text_xian.setVisibility(View.VISIBLE);
              imagejj.setVisibility(View.VISIBLE);
              oo.setVisibility(View.GONE);
          }


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
