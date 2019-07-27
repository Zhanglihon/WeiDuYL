package zhang.bw.com.open_my.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.WddaBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.presenter.WddaPresenter;

public class WddaActivity extends WDActivity {

    @BindView(R2.id.wdda_bj)
    Button wddaBj;
    @BindView(R2.id.wdda_sc)
    Button wddaSc;
    @BindView(R2.id.wdda_tj)
    Button wddaTj;
    @BindView(R2.id.Nested_ysj)
    NestedScrollView NestedYsj;
    @BindView(R2.id.wdda_wsj)
    RelativeLayout wddaWsj;
    @BindView(R2.id.wdda_image_back)
    ImageView wddaImageBack;
    private PopupWindow window;
    private View inflate;
    private TextView wdda_qu;
    private TextView wdda_que;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdda;
    }

    @Override
    protected void initView() {
        wddaImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //添加档案
        wddaTj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WddaActivity.this, WdbjActivity.class);
                startActivity(intent);
                finish();
            }
        });
        loginBean = DaoMaster.newDevSession(WddaActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        //用户查看自己的档案
        WddaPresenter wddaPresenter = new WddaPresenter(new wdda());
        wddaPresenter.reqeust(loginBean.getId(), loginBean.getSessionId());
        //删除
        wddaSc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inflate = View.inflate(WddaActivity.this, R.layout.wdda_popup, null);
                wdda_qu = inflate.findViewById(R.id.wdda_qu);
                wdda_que = inflate.findViewById(R.id.wdda_que);
                window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                window.setTouchable(true);
                window.setFocusable(true);
                //window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lan_3087ea)));
                window.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                window.showAsDropDown(inflate, 100, 100);

                //点击取消按钮
                wdda_qu.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });

                //点击确认按钮
                wdda_que.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });

            }
        });
        //编辑
        wddaBj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WddaActivity.this, WdbjActivity.class);
                startActivity(intent);
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

    class wdda implements DataCall<WddaBean> {

        @Override
        public void success(WddaBean data, Object... args) {
            if (data == null) {
                NestedYsj.setVisibility(View.GONE);
            } else {
                wddaWsj.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
