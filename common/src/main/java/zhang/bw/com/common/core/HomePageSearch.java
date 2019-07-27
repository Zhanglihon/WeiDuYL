package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class HomePageSearch extends WDPresenter<IAppRequest> {
    public HomePageSearch(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.homePageSearch((String)args[0]);
    }
}
