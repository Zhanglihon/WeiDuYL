package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class CancelVideoCollection extends WDPresenter<IAppRequest> {
    public CancelVideoCollection(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.cancelVideoCollection((long)args[0],(String)args[1],(String)args[2]);
    }
}
