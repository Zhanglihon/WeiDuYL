package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindUserWallet1 extends WDPresenter<IAppRequest> {
    public FindUserWallet1(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findUserWallet1((long)args[0],(String)args[1]);
    }
}
