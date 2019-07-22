package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class VideoBuy extends WDPresenter<IAppRequest>{
    public VideoBuy(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.videoBuy((long)args[0],(String)args[1],(String)args[2],(int)args[3]);
    }
}
