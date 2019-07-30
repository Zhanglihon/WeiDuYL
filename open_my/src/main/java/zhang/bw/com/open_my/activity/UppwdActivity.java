package zhang.bw.com.open_my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.RsaCoder;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.presenter.UppwdPresenter;

public class UppwdActivity extends WDActivity {


    @BindView(R2.id.uppwd_image_back)
    ImageView uppwdImageBack;
    @BindView(R2.id.uppwd_edit_oldpwd)
    EditText uppwdEditOldpwd;
    @BindView(R2.id.uppwd_new_pwd)
    EditText uppwdNewPwd;
    @BindView(R2.id.uppwd_edit_pwd2)
    EditText uppwdEditPwd2;
    @BindView(R2.id.uppwd_confirm)
    Button uppwdConfirm;
    private UppwdPresenter uppwdPresenter;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_uppwd;
    }

    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(UppwdActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        uppwdPresenter = new UppwdPresenter(new uppwd());
        uppwdConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpwd=uppwdEditOldpwd.getText().toString();
                try {
                    String s = RsaCoder.encryptByPublicKey(oldpwd);
                    String newpwd=uppwdEditPwd2.getText().toString();
                    String s1 = RsaCoder.encryptByPublicKey(newpwd);
                    uppwdPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),s,s1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    protected void destoryData() {

    }
    class uppwd implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(UppwdActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(UppwdActivity.this, "密码修改失败", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
