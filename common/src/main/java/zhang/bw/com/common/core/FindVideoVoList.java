package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindVideoVoList extends WDPresenter<IAppRequest> {
    public FindVideoVoList(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findVideoVoList((long)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4]);
    }
}
