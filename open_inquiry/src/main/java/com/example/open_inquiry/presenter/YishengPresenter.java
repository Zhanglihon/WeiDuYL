package com.example.open_inquiry.presenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
public class YishengPresenter extends WDPresenter<IAppRequest> {
    public YishengPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.Yisheng((long) args[0],(String)args[1],(String)args[2],(String)args[3]
                ,(String)args[4],(String)args[5],(String)args[6]);
    }
}
