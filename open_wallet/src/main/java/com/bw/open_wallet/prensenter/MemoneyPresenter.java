package com.bw.open_wallet.prensenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/16
 * @Description：XXXX
 */
public class MemoneyPresenter extends WDPresenter<IAppRequest>{

    public MemoneyPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.Memoney((long) args[0],(String) args[1]);
    }
}
