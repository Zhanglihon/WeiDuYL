package zhang.bw.com.open_login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
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
    @BindView(R2.id.login_radiobutton_eyes)
    CheckBox loginRadiobuttonEyes;
    private LoginBeanDao loginBeanDao;
    private IWXAPI api;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //注册
        Thread.setDefaultUncaughtExceptionHandler(new MyException(this));
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
        loginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginRadiobuttonEyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (loginRadiobuttonEyes.isChecked()){
                    loginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    loginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        loginForgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgeActivity.class);
                startActivity(intent);
            }
        });
        loginBeanDao = DaoMaster.newDevSession(LoginActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pwd = loginPwd.getText().toString();
                if (email.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"邮箱号或密码输入不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginPresenter loginPresenter = new LoginPresenter(new dl());
                try {
                    String s = RsaCoder.encryptByPublicKey(pwd);
                    loginPresenter.reqeust(email, s);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        loginLjzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        loginWxdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // send oauth request
                api = WXAPIFactory.createWXAPI(LoginActivity.this, "wxe3fcbe8a55cd33ff", true);
                // 将应用的appId注册到微信
                api.registerApp("wxe3fcbe8a55cd33ff");
//                // 通过WXAPIFactory工厂，获取IWXAPI的实例
//                SendAuth.Req req = new SendAuth.Req();
//                req.scope = "snsapi_userinfo";
//                api.sendReq(req);

                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";//
//                req.scope = "snsapi_login";//提示 scope参数错误，或者没有scope权限
                req.state = "wechat_sdk_微信登录";
                api.sendReq(req);



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

    class dl implements DataCall<LoginBean> {
        @Override
        public void success(LoginBean data, Object... args) {
            Toast.makeText(LoginActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
            data.datas = 1;
            Log.i("aaa",data.id+"-----"+data.sessionId);
            loginBeanDao.insertOrReplaceInTx(data);
            String sessionId = data.sessionId;
            long id = data.id;
            ARouter.getInstance().build(Constant.ACTIVITY_URL_MY).navigation();
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.i("aaa", "123" + data.getDisplayMessage());
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
