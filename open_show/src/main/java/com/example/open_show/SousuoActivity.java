package com.example.open_show;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import adapter.BingYouAdaoter;
import adapter.HistorySearchAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import presenter.Guanjianzi;
import uitl.HistorySearchUtil;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/21
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_SOUSUO)
public class SousuoActivity extends WDActivity {
    private EditText searchEdit;//搜索EditText
    private TextView searchTv,text_no;//搜索按钮，不过是以TextView形式
    private RecyclerView histotyRecycler,recyc_view2;//历史纪录列表
    private TextView historyEmptyTv;//清空历史纪录按钮
    private LinearLayout histotySearchLayout,linearLayout,linearLayout_1;//历史记录整个布局
    private HistorySearchAdapter adapter;//适配器
    private ArrayList<String> histotyList = new ArrayList<String>();//历史纪录数组
    private BingYouAdaoter bingYouAdaoter;
    private Guanjianzi guanjianzi;
    private ImageView image_haid_sou;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_sousuo;
    }

    @Override
    protected void initView() {
        initViews();//初始化组件
        initHistoryRecycler();//初始化historyRecyclerView
        getHistoryList();//得到历史记录数组
        setSearchTvListener();//设置搜索按钮监听器
        setHistoryEmptyTvListener();//设置清空记录按钮监听器
        guanjizi();

        searchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEdit.setFocusable(true);
                searchEdit.setFocusableInTouchMode(true);
                searchEdit.requestFocus();
            }
        });
        image_haid_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @SuppressLint("WrongConstant")
    private void guanjizi() {
        guanjianzi = new Guanjianzi(new request());

        LinearLayoutManager  linearLayoutManagers = new LinearLayoutManager(this);
        linearLayoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        recyc_view2.setLayoutManager(linearLayoutManagers);
        bingYouAdaoter = new BingYouAdaoter(this);
        recyc_view2.setAdapter(bingYouAdaoter);
    }

    private void initViews() {
        searchEdit = findViewById(R.id.search_edit);
        searchTv =  findViewById(R.id.search_tv);
        historyEmptyTv = findViewById(R.id.history_empty_tv);
        histotyRecycler = findViewById(R.id.history_search_recycler);
        histotySearchLayout = findViewById(R.id.history_search_layout);
        recyc_view2 = findViewById(R.id.recyc_view2);
        image_haid_sou = findViewById(R.id.image_haid_sou);
        text_no = findViewById(R.id.text_no);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout_1 = findViewById(R.id.linearLayout_1);
    }
    private void setHistoryEmptyTvListener() {
        historyEmptyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistorySearchUtil.getInstance(SousuoActivity.this)
                        .deleteAllHistorySearch();
                getHistoryList();
                adapter.notifyDataSetChanged();//刷新列表
                showViews();
            }
        });
    }

    private void setSearchTvListener() {
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = searchEdit.getText().toString();
                if(ss.length()!=0){
                    HistorySearchUtil.getInstance(SousuoActivity.this)
                            .putNewSearch(ss);//保存记录到数据库
                    getHistoryList();
                    adapter.notifyDataSetChanged();
                    guanjianzi.reqeust(ss);
                    text_no.setText("抱歉！没有找到“"+ss+"”相关的病友圈");
                    showViews();
                }else{
                    Toast.makeText(SousuoActivity.this, "请输入内容",
                            Toast.LENGTH_SHORT).show();
                }
                searchEdit.setText("");
                searchEdit.setFocusable(false);
                searchEdit.setFocusableInTouchMode(false);
                searchEdit.clearFocus();
            }
        });
    }

    /**
     * 设置历史记录界面可见性，即记录为空时，不显示清空历史记录按钮等view
     */
    private void showViews() {
        if (histotyList.size() > 0) {
            histotySearchLayout.setVisibility(View.VISIBLE);
            searchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // 此处为得到焦点时的处理内容
                        histotySearchLayout.setVisibility(View.VISIBLE);
                        recyc_view2.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    } else {
                        // 此处为失去焦点时的处理内容
                        histotySearchLayout.setVisibility(View.GONE);
                        recyc_view2.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else {
            histotySearchLayout.setVisibility(View.GONE);
        }


    }

    @SuppressLint("WrongConstant")
    private void initHistoryRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        histotyRecycler.setLayoutManager(layoutManager);
        histotyRecycler.setNestedScrollingEnabled(false);//解决滑动冲突
        adapter = new HistorySearchAdapter(this, histotyList);
        histotyRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new HistorySearchAdapter.OnItemClickListener() {
            @Override
            public void onItemNameTvClick(View v, String name) {
                searchEdit.setText(name);
            }

            @Override
            public void onItemDeleteImgClick(View v, String name) {
                HistorySearchUtil.getInstance(SousuoActivity.this)
                        .deleteHistorySearch(name);
                getHistoryList();
                adapter.notifyDataSetChanged();
                showViews();
            }
        });
    }


    private void getHistoryList() {
        histotyList.clear();
        histotyList.addAll(HistorySearchUtil.getInstance(this)
                .queryHistorySearchList());
        adapter.notifyDataSetChanged();
        showViews();
    }

    @Override
    protected void destoryData() {

    }

    private class request implements DataCall<List<Byliebiao>> {
        @Override
        public void success(List<Byliebiao> data, Object... args) {
            if (data.size() == 0) {
                linearLayout_1.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);

            } else {
                bingYouAdaoter.addalter(data);
                bingYouAdaoter.notifyDataSetChanged();
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout_1.setVisibility(View.GONE);

            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
