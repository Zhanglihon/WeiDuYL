package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindDrugsKnowledge extends WDPresenter<IAppRequest> {
    public FindDrugsKnowledge(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDrugsKnowledge((String)args[0]);
    }
}
