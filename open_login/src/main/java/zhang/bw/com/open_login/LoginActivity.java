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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.common.util.MD5Utils;
import zhang.bw.com.common.util.RsaCoder;
import zhang.bw.com.open_login.presenter.LoginPresenter;
@Route(path = Constant.ACTIVITY_URL_LOGIN)
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
    private LoginBeanDao loginBeanDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginBeanDao = DaoMaster.newDevSession(LoginActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao();
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
    class dl implements DataCall<LoginBean>{
        @Override
        public void success(LoginBean data, Object... args) {
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

            Log.i("aaa",data.id+"-----"+data.sessionId);
            ARouter.getInstance().build(Constant.ACTIVITY_URL_SHOW).navigation();
            loginBeanDao.insertOrReplaceInTx(data);
            String sessionId = data.sessionId;
            long id=data.id;
            Toast.makeText(LoginActivity.this,sessionId+"---------"+id,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.i("aaa","123"+data.getDisplayMessage());

            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();

        }
    }
}
