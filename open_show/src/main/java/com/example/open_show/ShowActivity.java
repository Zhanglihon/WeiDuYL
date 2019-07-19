package com.example.open_show;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.Fragmentfore;
import fragment.Fragmentone;
import fragment.Fragmentthree;
import fragment.Fragmenttwo;
import zhang.bw.com.common.util.Constant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Route(path =Constant.ACTIVITY_URL_SHOW)
public class ShowActivity extends AppCompatActivity {
    @BindView(R2.id.btn1)
    RadioButton btn1;
    @BindView(R2.id.btn2)
    RadioButton btn2;
    @BindView(R2.id.btn3)
    RadioButton btn3;
    @BindView(R2.id.frameLayout)
    FrameLayout frameLayout;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        fragments.add(new Fragmentone());
        fragments.add(new Fragmenttwo());
        fragments.add(new Fragmentthree());
        fragments.add(new Fragmentfore());

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,fragments.get(0)).commit();

    }

    @OnClick({R2.id.btn1, R2.id.btn2, R2.id.btn3})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragments.get(0)).commit();
            btn2.setChecked(false);
            btn3.setChecked(false);

        } else if (i == R.id.btn2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragments.get(1)).commit();
            btn3.setChecked(false);
            btn1.setChecked(false);

        } else if (i == R.id.btn3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragments.get(2)).commit();
            btn2.setChecked(false);
            btn1.setChecked(false);
        }
    }



}
