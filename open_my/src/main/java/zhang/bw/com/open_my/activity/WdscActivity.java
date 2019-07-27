package zhang.bw.com.open_my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.open_my.Flag.Jiankan_ShipingFra;
import zhang.bw.com.open_my.Flag.Jiankan_bingYouFra;
import zhang.bw.com.open_my.Flag.Jiankan_zixunFra;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;

public class WdscActivity extends WDActivity {

    @BindView(R2.id.btnn_1)
    RadioButton btnn1;
    @BindView(R2.id.btnn_2)
    RadioButton btnn2;
    @BindView(R2.id.btnn_3)
    RadioButton btnn3;
    @BindView(R2.id.shouchneg_radio_group)
    RadioGroup shouchnegRadioGroup;
    @BindView(R2.id.qqqqqqqq)
    RelativeLayout qqqqqqqq;
    @BindView(R2.id.shouchuang_frament)
    FrameLayout shouchuangFrament;
    @BindView(R2.id.wdsc_image_back)
    ImageView wdscImageBack;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentTransaction fragmentTransaction1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdsc;
    }

    @Override
    protected void initView() {
        wdscImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        final Jiankan_zixunFra jiankan_zixunFra = new Jiankan_zixunFra();
        final Jiankan_ShipingFra jiankan_shipingFra = new Jiankan_ShipingFra();
        final Jiankan_bingYouFra jiankan_bingYouFra = new Jiankan_bingYouFra();

        fragmentTransaction.add(R.id.shouchuang_frament,jiankan_zixunFra);
        fragmentTransaction.add(R.id.shouchuang_frament,jiankan_shipingFra);
        fragmentTransaction.add(R.id.shouchuang_frament,jiankan_bingYouFra);

        fragmentTransaction.show(jiankan_zixunFra).hide(jiankan_shipingFra).hide(jiankan_bingYouFra).commit();
        shouchnegRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction1=fragmentManager.beginTransaction();
                if (checkedId == R.id.btnn_1) {
                    fragmentTransaction1.show(jiankan_zixunFra).hide(jiankan_shipingFra).hide(jiankan_bingYouFra).
                            commit();
                }else if (checkedId==R.id.btnn_2){
                    fragmentTransaction1.show(jiankan_shipingFra).hide(jiankan_zixunFra).hide(jiankan_bingYouFra).commit();
                }else if (checkedId==R.id.btnn_3){
                    fragmentTransaction1.show(jiankan_bingYouFra).hide(jiankan_zixunFra).hide(jiankan_shipingFra).commit();
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
}
