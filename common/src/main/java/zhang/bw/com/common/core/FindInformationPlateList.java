package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindInformationPlateList extends WDPresenter<IAppRequest>{
    public FindInformationPlateList(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findInformationPlateList();
    }
}
