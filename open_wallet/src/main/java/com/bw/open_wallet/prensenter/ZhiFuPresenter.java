package com.bw.open_wallet.prensenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/16
 * @Description：类
 */
public class ZhiFuPresenter extends WDPresenter<IAppRequest> {
    public ZhiFuPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.zhifujiekou((long) args[0],(String)args[1],(String)args[2],(String)args[3]);
    }
}
