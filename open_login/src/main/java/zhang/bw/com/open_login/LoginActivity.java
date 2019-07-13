package zhang.bw.com.open_login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.MD5Utils;
import zhang.bw.com.common.util.RsaCoder;
import zhang.bw.com.open_login.presenter.LoginPresenter;

public class LoginActivity extends WDActivity {

    @BindView(R2.id.login_email)
    EditText loginEmail;
    @BindView(R2.id.login_pwd)
    EditText loginPwd;
    @BindView(R2.id.login_dl)
    Button loginDl;
    @BindView(R2.id.login_forgetpwd)
    TextView loginForgetpwd;
    @BindView(R2.id.login_ljzc)
    TextView loginLjzc;
    @BindView(R2.id.login_wxdl)
    ImageView loginWxdl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=loginEmail.getText().toString();
                String pwd=loginPwd.getText().toString();
                LoginPresenter loginPresenter=new LoginPresenter(new dl());
                try {
                    String s = RsaCoder.encryptByPublicKey(pwd);
                    loginPresenter.reqeust(email,s);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        loginLjzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
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
    class dl implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.i("aaa","123"+data.getDisplayMessage());

            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();

        }
    }
}
