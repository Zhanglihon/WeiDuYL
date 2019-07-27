package com.example.open_show;

import adapter.BaseRecycleAdapter;
import adapter.MyShouAdapter;
import adapter.MyShouAdapter2;
import adapter.MyShouAdapter3;
import adapter.SeachRecordAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.bean.ShoucuoBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.HomePageSearch;
import zhang.bw.com.common.core.exception.ApiException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R2.id.zidingyi1)
     FlowLabout mFlowLayout;
    @BindView(R2.id.oo)
    TextView oo;
    private DbDao mDbDao;
    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private SeachRecordAdapter mAdapter;
    @BindView(R2.id.texth)
    TextView texth;
    @SuppressLint("WrongConstant")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucuo);
         ButterKnife.bind(this);
        mDbDao =new DbDao(this);
         homePageSearch = new HomePageSearch(new Backc());
        shou_recyc.setLayoutManager(new LinearLayoutManager(ShoucuoActivity.this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =new SeachRecordAdapter(mDbDao.queryData(""),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ShoucuoActivity.this,LinearLayoutManager.VERTICAL,false));
        initChildViews();
    }
    private void initChildViews() {
        // TODO Auto-generated method stub
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setTextSize(14);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.aa2));
            mFlowLayout.addView(view,lp);
        }

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

    class Backc implements DataCall<ShoucuoBean>{
        @SuppressLint("WrongConstant")
        @Override
        public void success(ShoucuoBean data, Object... args) {
          if(s.equals("病")){
                         texth.setVisibility(View.GONE);
                        shou_recyc.setVisibility(View.VISIBLE);
                        imagejj.setVisibility(View.GONE);
                        text_xian.setVisibility(View.GONE);
                        mFlowLayout.setVisibility(View.GONE);
                        oo.setVisibility(View.GONE);
                        myShouAdapter = new MyShouAdapter(ShoucuoActivity.this);
                        shou_recyc.setAdapter(myShouAdapter);
                        myShouAdapter.addAll(data);
                        myShouAdapter.notifyDataSetChanged();
                    }else if(s.equals("医")){
                       texth.setVisibility(View.GONE);
                       shou_recyc.setVisibility(View.VISIBLE);
                       imagejj.setVisibility(View.GONE);
                       mFlowLayout.setVisibility(View.GONE);
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
                        mFlowLayout.setVisibility(View.GONE);
                        text_xian.setVisibility(View.GONE);
                        oo.setVisibility(View.GONE);
                        myShouAdapter3 = new MyShouAdapter3(ShoucuoActivity.this);
                        shou_recyc.setAdapter(myShouAdapter3);
                        myShouAdapter3.addAll2(data);
                        myShouAdapter3.notifyDataSetChanged();
                    }else {
              mRecyclerView.setVisibility(View.GONE);
              texth.setVisibility(View.GONE);
              shou_recyc.setVisibility(View.GONE);
              mFlowLayout.setVisibility(View.GONE);
              text_xian.setVisibility(View.VISIBLE);
              imagejj.setVisibility(View.VISIBLE);
              oo.setVisibility(View.GONE);
          }


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    private String mNames[] = {
            "阿胶","小可爱医生","感冒",
            "头痛","神经病","发烧 癫疯",
            "阿莫西林","脱发","腿抽筋",
            "小儿感冒颗粒","神经炎"
    };

}
