package zhang.bw.com.open_my;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_my.presenter.WdncPresenter;

public class WdncActivity extends WDActivity {

    @BindView(R2.id.wdnc_image_back)
    ImageView wdncImageBack;
    @BindView(R2.id.wdnc_wc)
    Button wdncWc;
    @BindView(R2.id.wdnc_edit)
    EditText wdncEdit;
    @BindView(R2.id.wdnc_clear)
    ImageView wdncClear;
    private View inflate;
    private TextView wdda_qu;
    private TextView wdda_que;
    private PopupWindow window;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdnc;
    }

    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(WdncActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        wdncImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wdncClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wdncEdit.setText(null);
            }
        });
        wdncWc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inflate = View.inflate(WdncActivity.this,R.layout.wdda_popup, null);
                wdda_qu = inflate.findViewById(R.id.wdda_qu);
                wdda_que = inflate.findViewById(R.id.wdda_que);
                window = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                window.setTouchable(true);
                window.setFocusable(true);
                //window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lan_3087ea)));
                window.showAtLocation(inflate, Gravity.CENTER,0,0);
                window.showAsDropDown(inflate,100,100);

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
                        String name=wdncEdit.getText().toString();
                        WdncPresenter wdncPresenter=new WdncPresenter(new wdnc());
                        wdncPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),name);
                    }
                });

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
    class wdnc implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(WdncActivity.this, "昵称修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(WdncActivity.this, "昵称修改失败", Toast.LENGTH_SHORT).show();
        }
    }
}
