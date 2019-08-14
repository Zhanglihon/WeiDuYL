package com.example.health.presenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

public class EvaluationInquiry extends WDPresenter<IAppRequest> {
    public EvaluationInquiry(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.evaluationInquiry((long)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5],(String)args[6]);
    }
}
