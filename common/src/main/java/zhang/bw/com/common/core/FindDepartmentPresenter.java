package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindDepartmentPresenter extends WDPresenter<IAppRequest> {
    public FindDepartmentPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDepartment();
    }
}
