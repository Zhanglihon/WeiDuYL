package zhang.bw.com.open_main;


import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.WDApplication;
import zhang.bw.com.common.util.Constant;


@Route(path =Constant.ACTIVITY_URL_MAIN)
public class MainActivity extends WDActivity {


    @BindView(R2.id.tt1)
    TextView tt1;
    @BindView(R2.id.tt2)
    TextView tt2;
    @BindView(R2.id.viewGroup)
    LinearLayout viewGroup;
    @BindView(R2.id.bb1)
    RadioButton bb1;
    @BindView(R2.id.bb2)
    RadioButton bb2;
    @BindView(R2.id.bb3)
    RadioButton bb3;
    @BindView(R2.id.bb4)
    RadioButton bb4;
    @BindView(R2.id.bb5)
    RadioButton bb5;
    @BindView(R2.id.bb6)
    RadioButton bb6;
    @BindView(R2.id.group)
    RadioGroup group;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    @BindView(R2.id.but_ru)
    Button butRu;

    private String[] picTrueArr;
    private boolean isAutoPlay;
    private ArrayList<View> pageview;
    private SharedPreferences sp;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_main;
    }

    @Override
    protected void initView() {

        sp = getSharedPreferences("logu", MODE_PRIVATE);
        boolean b = sp.getBoolean("edit", false);
        if (b){
            ARouter.getInstance().build(Constant.ACTIVITY_URL_SHOW).navigation();
        }


        tt1.setText("");
        tt2.setText("八维移动通讯学院学生作品");
        List images = new ArrayList<>();
        images.add(R.mipmap.longtu1);
        images.add(R.mipmap.longtu1);
        images.add(R.mipmap.longtu2);
        images.add(R.mipmap.longtu3);
        images.add(R.mipmap.longtu4);
        images.add(R.mipmap.longtu5);
        images.add(R.mipmap.longtu6);


        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item01, null);
        View view2 = inflater.inflate(R.layout.item02, null);
        View view3 = inflater.inflate(R.layout.item03, null);
        View view4 = inflater.inflate(R.layout.item04, null);
        View view5 = inflater.inflate(R.layout.item05, null);
        View view6 = inflater.inflate(R.layout.item06, null);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Main2Activity.this, LoginActivity.class));
            }
        });

        pageview = new ArrayList<View>();
        pageview.add(view1);
        pageview.add(view2);
        pageview.add(view3);
        pageview.add(view4);
        pageview.add(view5);
        pageview.add(view6);

        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageview.size();
            }

            @Override
            //断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            //是从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageview.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(pageview.get(arg1));
                return pageview.get(arg1);
            }


        };

        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                group.check(group.getChildAt(i).getId());

                if (i == 0) {
                    tt1.setText("");
                    tt2.setText("八维移动通讯学院学生作品");
                } else if (i == 1) {
                    tt1.setText("丰富的健康常识");
                    tt2.setText("");
                } else if (i == 2) {
                    tt1.setText("专业在线问诊");
                    tt2.setText("");
                } else if (i == 3) {
                    tt1.setText("丰富的健康知识");
                    tt2.setText("");
                } else if (i == 4) {
                    tt1.setText("专业在线问诊");
                    tt2.setText("");
                } else if (i == 5) {
                    tt1.setText("打造你的健康常青树");
                    tt2.setText("");

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bb1) {
                    viewPager.setCurrentItem(0, false);
                    group.setVisibility(View.GONE);
                    butRu.setVisibility(View.GONE);


                } else if (checkedId == R.id.bb2) {
                    viewPager.setCurrentItem(1, false);
                    group.setVisibility(View.VISIBLE);
                    butRu.setVisibility(View.GONE);

                } else if (checkedId == R.id.bb3) {
                    viewPager.setCurrentItem(2, false);
                    group.setVisibility(View.VISIBLE);
                    butRu.setVisibility(View.GONE);

                } else if (checkedId == R.id.bb4) {
                    viewPager.setCurrentItem(3, false);
                    group.setVisibility(View.VISIBLE);
                    butRu.setVisibility(View.GONE);

                } else if (checkedId == R.id.bb5) {
                    viewPager.setCurrentItem(4, false);
                    group.setVisibility(View.VISIBLE);
                    butRu.setVisibility(View.GONE);
                } else if (checkedId == R.id.bb6) {
                    viewPager.setCurrentItem(5, false);
                    group.setVisibility(View.VISIBLE);
                    butRu.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    @Override
    protected void destoryData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.but_ru)
    public void onViewClicked() {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("edit",true);
        edit.apply();
        //跳转到首页
    ARouter.getInstance().build(Constant.ACTIVITY_URL_SHOW).navigation();
        finish();


    }
}
