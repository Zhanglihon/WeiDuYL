package zhang.bw.com.open_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.open_login.presenter.ForgePresenter;
import zhang.bw.com.open_login.presenter.SendEmailPresenter;

public class ForgeActivity extends WDActivity {

    @BindView(R2.id.forge_email)
    EditText forgeEmail;
    @BindView(R2.id.forge_button_yzm)
    Button forgeButtonYzm;
    @BindView(R2.id.forge_yzm)
    EditText forgeYzm;
    @BindView(R2.id.forge_next)
    Button forgeNext;
    private SendEmailPresenter sendEmailPresenter;
    private String yx;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forge;
    }

    @Override
    protected void initView() {
        forgeButtonYzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailPresenter = new SendEmailPresenter(new sendemail());
                String email=forgeEmail.getText().toString();
                sendEmailPresenter.reqeust(email);
            }
        });
        forgeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yzm=forgeYzm.getText().toString();
                yx = forgeEmail.getText().toString();
                ForgePresenter forgePresenter=new ForgePresenter(new forge());
                if (yzm.isEmpty()||yx.isEmpty()){
                    Toast.makeText(ForgeActivity.this, "邮箱和验证码输入不能为空哦", Toast.LENGTH_SHORT).show();
                }
                forgePresenter.reqeust(yx,yzm);
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
    class sendemail implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(ForgeActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(ForgeActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
        }
    }
    class forge implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(ForgeActivity.this,"验证通过",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(ForgeActivity.this,CzpwdActivity.class);
            intent.putExtra("yx",yx);
            startActivity(intent);
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(ForgeActivity.this,"验证失败",Toast.LENGTH_SHORT).show();
        }
    }
}
