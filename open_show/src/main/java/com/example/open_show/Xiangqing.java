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
import zhang.bw.com.common.core.AddInfoCollection;
import zhang.bw.com.common.core.CancelInfoCollection;
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
    @BindView(R2.id.xing)
    ImageView xing;
    AddInfoCollection addInfoCollection;
    CancelInfoCollection cancelInfoCollection;
    private String rr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(Xiangqing.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        rr = getIntent().getStringExtra("rr");
        findInformation = new FindInformation(new Backj());
        addInfoCollection = new AddInfoCollection(new Backc());
        cancelInfoCollection = new CancelInfoCollection(new Backw());
        findInformation.reqeust(loginBean.getId(),loginBean.getSessionId(), rr);
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
            if(data.whetherCollection == 0){
                xing.setBackgroundResource(R.mipmap.common_button_collection_large_n);
            }
            if(data.whetherCollection == 1){
                xing.setBackgroundResource(R.mipmap.common_button_collection_large_s);
            }
           xing.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(data.whetherCollection == 0){
                       addInfoCollection.reqeust(loginBean.getId(),loginBean.getSessionId(),rr);
                       findInformation.reqeust(loginBean.getId(),loginBean.getSessionId(), rr);
                   }
                   if(data.whetherCollection == 1){
                       cancelInfoCollection.reqeust(loginBean.getId(),loginBean.getSessionId(),rr);
                       findInformation.reqeust(loginBean.getId(),loginBean.getSessionId(), rr);
                   }

               }
           });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backc implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class Backw implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
