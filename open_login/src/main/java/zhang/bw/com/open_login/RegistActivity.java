package zhang.bw.com.open_login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.MD5Utils;
import zhang.bw.com.common.util.RsaCoder;
import zhang.bw.com.open_login.presenter.RegistPresenter;
import zhang.bw.com.open_login.presenter.SendEmailPresenter;

public class RegistActivity extends WDActivity {

    @BindView(R2.id.regist_email)
    EditText registEmail;
    @BindView(R2.id.regist_button_yzm)
    Button registButtonYzm;
    @BindView(R2.id.regist_yzm)
    EditText registYzm;
    @BindView(R2.id.regist_pwd)
    EditText registPwd;
    @BindView(R2.id.regist_radiobutton_eyes)
    RadioButton registRadiobuttonEyes;
    @BindView(R2.id.regist_pwd1)
    EditText registPwd1;
    @BindView(R2.id.regist_radiobutton_eyes1)
    RadioButton registRadiobuttonEyes1;
    @BindView(R2.id.regist_yqm)
    EditText registYqm;
    @BindView(R2.id.regist_zc)
    Button registZc;
    private SendEmailPresenter sendEmailPresenter;
    private String s1;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        sendEmailPresenter = new SendEmailPresenter(new sendemail());
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        registButtonYzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=registEmail.getText().toString();
                sendEmailPresenter.reqeust(email);
            }
        });
        registZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到输入框的值
                String email=registEmail.getText().toString();
                String yzm=registYzm.getText().toString();
                String pwd=registPwd.getText().toString();
                String pwd1=registPwd.getText().toString();
                RegistPresenter registPresenter=new RegistPresenter(new regist());

                if (pwd.equals(pwd1)){
                    try {
                        String s = RsaCoder.encryptByPublicKey(pwd);
                        registPresenter.reqeust(email,yzm,s,s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e("aaa",s+"======="+s1);
                    Toast.makeText(RegistActivity.this,"两次密码一样",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegistActivity.this,"两次密码不一样",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void destoryData() {

    }


    class sendemail implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(RegistActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(RegistActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
        }
    }
    class regist implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(RegistActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }
}
