package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FollowDoctor extends WDPresenter<IAppRequest> {

    public FollowDoctor(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return  iRequest.followDoctor((long)args[0],(String)args[1],(String)args[2]);
    }
}
