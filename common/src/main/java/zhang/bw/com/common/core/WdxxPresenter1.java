package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class WdxxPresenter1  extends WDPresenter<IAppRequest>{
    public WdxxPresenter1(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getUserInfoById1((long)args[0],(String)args[1]);
    }
}
