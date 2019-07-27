package zhang.bw.com.open_login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.common.util.RsaCoder;
import zhang.bw.com.open_login.presenter.CzPwdPresenter;

public class CzpwdActivity extends WDActivity {

    @BindView(R2.id.czpwd_pwd)
    EditText czpwdPwd;
    @BindView(R2.id.czpwd_radiobutton_eyes)
    RadioButton czpwdRadiobuttonEyes;
    @BindView(R2.id.czpwd_yzm)
    EditText czpwdYzm;
    @BindView(R2.id.czpwd_radiobutton_eyes2)
    RadioButton czpwdRadiobuttonEyes2;
    @BindView(R2.id.czpwd_wc)
    Button czpwdWc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_czpwd;
    }

    @Override
    protected void initView() {
        czpwdWc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CzPwdPresenter czPwdPresenter=new CzPwdPresenter(new czpwd());
                String pwd=czpwdPwd.getText().toString();
                String newpwd=czpwdYzm.getText().toString();
                try {
                    String s = RsaCoder.encryptByPublicKey(pwd);
                    Intent intent=getIntent();
                    String yx = intent.getStringExtra("yx");
                    czPwdPresenter.reqeust(yx,s,s);
                    Log.i("ccc",yx+"----"+s);
                } catch (Exception e) {
                    e.printStackTrace();
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

    class czpwd implements DataCall {

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(CzpwdActivity.this, "重置密码成功", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_LOGIN).navigation();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(CzpwdActivity.this, "重置密码失败", Toast.LENGTH_SHORT).show();
        }
    }
}
