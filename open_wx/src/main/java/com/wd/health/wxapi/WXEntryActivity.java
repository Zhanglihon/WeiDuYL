package com.wd.health.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/8/6
 * @Description：XXXX
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, "wxe3fcbe8a55cd33ff",true);
        //将应用的appid注册到微信
        api.registerApp("wxe3fcbe8a55cd33ff");

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            if(!result){
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result = "";
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result ="发送成功";
                String code = ((SendAuth.Resp) baseResp).code;
                Log.e("aaa",code);
//                MainPresenterIml mainPresenterIml = new MainPresenterIml(new MainModelIml(),this);
//                String  url = "movieApi/user/v1/weChatBindingLogin";
//                Map<String,String> map = new HashMap<>();
//                map.put("code",code);
//                mainPresenterIml.post(1111,url,new HashMap<>(),map);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";

                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                finish();
                break;
            default:
                result = "发送返回";
                finish();
                break;
        }

    }


}
