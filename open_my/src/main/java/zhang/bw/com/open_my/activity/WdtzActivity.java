package zhang.bw.com.open_my.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.presenter.WdtzPresenter;
import zhang.bw.com.open_my.presenter.WdxxPresenter;

public class WdtzActivity extends WDActivity {

    @BindView(R2.id.wdtz_image_back)
    ImageView wdtzImageBack;
    @BindView(R2.id.wdtz_wc)
    Button wdtzWc;
    @BindView(R2.id.wdtz_sg)
    SeekBar wdtzSg;
    @BindView(R2.id.wdtz_tz)
    SeekBar wdtzTz;
    @BindView(R2.id.wdtz_age)
    SeekBar wdtzAge;
    @BindView(R2.id.num)
    TextView num;
    @BindView(R2.id.wdtz_text1)
    TextView wdtzText1;
    @BindView(R2.id.num2)
    TextView num2;
    @BindView(R2.id.wdtz_text2)
    TextView wdtzText2;
    @BindView(R2.id.num3)
    TextView num3;
    @BindView(R2.id.wdtz_text3)
    TextView wdtzText3;
    private LoginBean loginBean;
    private WdtzPresenter wdtzPresenter;
    private String aa;
    private String aa1;
    private String aa2;
    private WdxxPresenter wdxxPresenter;
    private int vv;
    private int weight;
    private int age;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdtz;
    }

    @Override
    protected void initView() {
        wdtzImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loginBean = DaoMaster.newDevSession(WdtzActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        wdtzPresenter = new WdtzPresenter(new wdtz());
        wdxxPresenter = new WdxxPresenter(new wdxx());
        wdxxPresenter.reqeust(loginBean.getId(), loginBean.getSessionId());

        //身高
        wdtzSg.setMax(250);
        wdtzSg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wdtzText1.setText(progress+"");
                num.setText(progress + "cm");
                aa = wdtzText1.getText().toString();
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                num.measure(spec, spec);
                int quotaWidth = num.getMeasuredWidth();

                int spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                num.measure(spec2, spec2);
                int sbWidth = wdtzSg.getMeasuredWidth();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) num.getLayoutParams();
                params.leftMargin = (int) (((double) progress / wdtzSg.getMax()) * sbWidth - (double) quotaWidth * progress / wdtzSg.getMax());
                num.setLayoutParams(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //体重
        wdtzTz.setMax(150);
        wdtzTz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wdtzText2.setText(progress + "");
                num2.setText(progress + "kg");
                aa1 = wdtzText2.getText().toString();
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                num2.measure(spec, spec);
                int quotaWidth = num2.getMeasuredWidth();

                int spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                num2.measure(spec2, spec2);
                int sbWidth = wdtzTz.getMeasuredWidth();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) num2.getLayoutParams();
                params.leftMargin = (int) (((double) progress / wdtzTz.getMax()) * sbWidth - (double) quotaWidth * progress / wdtzTz.getMax());
                num2.setLayoutParams(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //年龄
        wdtzAge.setMax(120);
        wdtzAge.setProgress(age);
        wdtzAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num3.setText(progress + "");
                aa2 = num3.getText().toString();
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                num3.measure(spec, spec);
                int quotaWidth = num3.getMeasuredWidth();

                int spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                num3.measure(spec2, spec2);
                int sbWidth = wdtzAge.getMeasuredWidth();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) num3.getLayoutParams();
                params.leftMargin = (int) (((double) progress / wdtzAge.getMax()) * sbWidth - (double) quotaWidth * progress / wdtzAge.getMax());
                num3.setLayoutParams(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        wdtzWc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("aaa", aa);
                wdtzPresenter.reqeust(loginBean.getId(), loginBean.getSessionId(), aa + "", aa1+"",aa2+"");
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

    class wdtz implements DataCall {

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(WdtzActivity.this, "用户信息完善成功", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(WdtzActivity.this, "用户信息完善失败", Toast.LENGTH_SHORT).show();
        }
    }
    class wdxx implements DataCall<CXBean> {
        @Override
        public void success(CXBean data, Object... args) {
            vv = data.height;
            num.setText(data.height+"cm");
            wdtzText1.setText(data.height+"");
            weight = data.weight;
            wdtzText2.setText(data.weight+"");
            num2.setText(data.weight+"kg");
            age = data.age;
            wdtzSg.setProgress(vv);
            wdtzTz.setProgress(weight);
            wdtzAge.setProgress(age);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
