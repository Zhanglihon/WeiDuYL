package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindDrugsKnowledgeList extends WDPresenter<IAppRequest> {
    public FindDrugsKnowledgeList(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDrugsKnowledgeList((String)args[0],(String) args[1],(String)args[2]);
    }
}
