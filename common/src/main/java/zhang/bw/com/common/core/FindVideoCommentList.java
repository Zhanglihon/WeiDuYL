package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindVideoCommentList extends WDPresenter<IAppRequest> {
    public FindVideoCommentList(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findVideoCommentList((String)args[0]);
    }
}
