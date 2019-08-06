package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindHistoryInquiryRecord extends WDPresenter<IAppRequest> {
    public FindHistoryInquiryRecord(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findHistoryInquiryRecord((long)args[0],(String)args[1],(String)args[2],(String)args[3]);
    }
}
