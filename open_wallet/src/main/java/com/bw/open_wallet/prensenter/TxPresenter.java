package com.bw.open_wallet.prensenter;

import io.reactivex.Observable;
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
public class TxPresenter extends WDPresenter<IAppRequest> {
    public TxPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.drawCash((long)args[0],(String)args[1],(int)args[2]);
    }
}
