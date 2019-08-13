package com.example.open_show;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.QingBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindInformation;
import zhang.bw.com.common.core.exception.ApiException;

public class Xiangqing extends AppCompatActivity {
    private FindInformation findInformation;
    private LoginBean loginBean;
    @BindView(R2.id.qing_text1)
    WebView webView;
    @BindView(R2.id.back)
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(Xiangqing.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        String rr = getIntent().getStringExtra("rr");
        findInformation = new FindInformation(new Backj());
        findInformation.reqeust(loginBean.getId(),loginBean.getSessionId(),rr);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    class Backj implements DataCall<QingBean> {

        @Override
        public void success(QingBean data, Object... args) {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            String js="<script type=\"text/javascript\">"+
                    "var imgs=document.getElementsByTagName('img');"+
                    "for(var i = 0; i<imgs.length; i++){"+
                    "imgs[i].style.width='100%';"+
                    "imgs[i].style.height='auto';"+
                    "}"+
                    "</script>";
            //这个就是HTML后缀的数据
            String details = data.content;
            webView.loadDataWithBaseURL(null, details + js + "</html></body>","text/html", "utf-8", null);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
