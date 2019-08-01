package com.example.open_show;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShouBanner extends AppCompatActivity {
    @BindView(R2.id.shou_web)
    WebView shou_web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_banner);
        ButterKnife.bind(this);
        String ff = getIntent().getStringExtra("ff");
        shou_web.loadUrl(ff);
        shou_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        shou_web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        shou_web.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
         shou_web.getSettings().setSupportZoom(true);
        // 扩大比例的缩放
         shou_web.getSettings().setUseWideViewPort(true);
        // 设置是否出现缩放工具
        shou_web.getSettings().setBuiltInZoomControls(true);


    }


}
