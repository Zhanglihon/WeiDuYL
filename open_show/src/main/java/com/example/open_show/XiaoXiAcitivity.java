package com.example.open_show;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mm.opensdk.utils.Log;

import java.util.List;
import java.util.function.LongFunction;

import androidx.appcompat.app.AppCompatActivity;
import presenter.XiaoXiPresetner;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.XiaoXiBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/8/8
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_XIAOXI)
public class XiaoXiAcitivity extends WDActivity{
    TextView text_numxt,text_numhb,text_numwz;
    private LoginBean loginBean;
    private long id;
    private String sessionId;
    ImageView image_fan;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_xiaoxi;

    }

    @Override
    protected void initView() {
        text_numhb=findViewById(R.id.text_numhb);
        text_numxt=findViewById(R.id.text_numxt);
        text_numwz=findViewById(R.id.text_numwz);
        image_fan=findViewById(R.id.image_fan);

        image_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginBean = DaoMaster.newDevSession(XiaoXiAcitivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        id = loginBean.getId();
        sessionId = loginBean.getSessionId();

        XiaoXiPresetner xiaoXiPresetner= new XiaoXiPresetner(new reqeust());
        xiaoXiPresetner.reqeust(id,sessionId);


    }

    @Override
    protected void destoryData() {

    }

    private class reqeust implements DataCall<List<XiaoXiBean>>{
        @Override
        public void success(List<XiaoXiBean> data, Object... args) {

            for (int i = 0; i < data.size(); i++) {
                Log.e("aaa",data.get(i).getNoticeType()+"");
                Log.e("aaa",data.get(i).getNotReadNum()+"");
            }
            int xt = data.get(0).getNotReadNum();
            int wz = data.get(1).getNotReadNum();
            int hb = data.get(2).getNotReadNum();
            if(xt!=0){
                text_numxt.setVisibility(View.VISIBLE);

                text_numxt.setText(xt+"");
            }else{
                text_numxt.setVisibility(View.INVISIBLE);
            }

            if(wz!=0){
                text_numwz.setVisibility(View.VISIBLE);

                text_numwz.setText(xt+"");
            }else{
                text_numwz.setVisibility(View.INVISIBLE);
            }

            if(hb!=0){
                text_numhb.setVisibility(View.VISIBLE);

                text_numhb.setText(xt+"");
            }else{
                text_numhb.setVisibility(View.INVISIBLE);
            }


            text_numxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(Constant.ACTIVITY_URL_XTXIAOXI).navigation();
                }
            });
            text_numhb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(Constant.ACTIVITY_URL_HBXIAOXI).navigation();
                }
            });
            text_numwz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(Constant.ACTIVITY_URL_WZXIAOXI).navigation();
                }
            });


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
