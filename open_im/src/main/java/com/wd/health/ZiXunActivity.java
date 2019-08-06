package com.wd.health;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.common.util.RsaCoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;


@Route(path = Constant.ACTIVITY_URL_FABIAOPINGLUN3)
public class ZiXunActivity extends AppCompatActivity {
    @Autowired
    public String data;
    @BindView(R2.id.fasong)
    ImageView fasong;
    @BindView(R2.id.edit_namew)
    EditText edit_namew;
    public String ss;
    public String appkey ="b5f102cc307091e167ce52e0";
    private LoginBean loginBean;
    private Conversation singleConversation;
    private String s1;
     @BindView(R2.id.yuyin)
    ImageView yuyin;
     @BindView(R2.id.jianpan)
    ImageView jianpan;
     @BindView(R2.id.anzhu)
    TextView anzhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun);
        ButterKnife.bind(this);
        loginBean =DaoMaster.newDevSession(ZiXunActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jianpan.setVisibility(View.VISIBLE);
                yuyin.setVisibility(View.GONE);
                edit_namew.setVisibility(View.GONE);
                anzhu.setVisibility(View.VISIBLE);
                jianpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jianpan.setVisibility(View.GONE);
                        yuyin.setVisibility(View.VISIBLE);
                        edit_namew.setVisibility(View.VISIBLE);
                        anzhu.setVisibility(View.GONE);
                    }
                });
            }
        });
        try {
            ss = RsaCoder.decryptByPublicKey(loginBean.jiGuangPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("tt",ss);
        s1 = MD5.MD5(ss);
        Log.e("aa",s1);
        Toast.makeText(ZiXunActivity.this,loginBean.userName,Toast.LENGTH_LONG).show();
        JMessageClient.login(loginBean.userName,s1 , new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Log.e("aa",i+"");
                switch (i) {
                    case 801003:
                        Toast.makeText(ZiXunActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                        break;
                    case 871301:
                        Toast.makeText(ZiXunActivity.this, "密码格式错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 801004:
                        Toast.makeText(ZiXunActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(ZiXunActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        Toast.makeText(ZiXunActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        singleConversation = Conversation.createSingleConversation("MQtmPd2295587818", appkey);
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edit_namew.getText().toString().trim();
                Message message = singleConversation.createSendMessage(new TextContent(text));
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseDesc) {
                        if (responseCode == 0) {
                            //消息发送成功
                            Toast.makeText(ZiXunActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                            edit_namew.setText("");
//                            //添加一下
                        } else {
                            //消息发送失败
                            Toast.makeText(ZiXunActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                JMessageClient.sendMessage(message);//使用默认控制参数发送消息

            }
        });


    }
    class Backt implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
