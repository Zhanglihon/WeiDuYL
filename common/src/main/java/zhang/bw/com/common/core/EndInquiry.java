package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class EndInquiry extends WDPresenter<IAppRequest> {
    public EndInquiry(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.endInquiry((long)args[0],(String)args[1],(String)args[2]);
    }
}
