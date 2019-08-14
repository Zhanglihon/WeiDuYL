package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class PushMessage extends WDPresenter<IAppRequest> {
    public PushMessage(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.pushMessage((long)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5],(String)args[6]);
    }
}
