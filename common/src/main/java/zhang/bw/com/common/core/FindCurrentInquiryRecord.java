package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindCurrentInquiryRecord extends WDPresenter<IAppRequest> {
    public FindCurrentInquiryRecord(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findCurrentInquiryRecord((long)args[0],(String)args[1]);
    }
}
