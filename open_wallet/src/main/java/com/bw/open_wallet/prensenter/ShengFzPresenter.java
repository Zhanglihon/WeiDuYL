package com.bw.open_wallet.prensenter;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class ShengFzPresenter extends WDPresenter<IAppRequest> {
    public ShengFzPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.bindUserIdCard((long)args[0],(String)args[1],(RequestBody)args[2]);
    }
}
