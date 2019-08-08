package com.bw.open_wallet.prensenter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.exception.ApiException;
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

    @Override
    protected Consumer getConsumer(Object... args) {
        return new Consumer<Result>() {
            @Override
            public void accept(Result result) throws Exception {

                if (result.getStatus().equals("0000")) {
                    dataCall.success(result);
                }else{
                    dataCall.fail(new ApiException(result.getStatus(),result.getMessage()));
                }
            }
        };
    }
}
