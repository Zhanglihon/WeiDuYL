package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindDiseaseCategory extends WDPresenter<IAppRequest> {
    public FindDiseaseCategory(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDiseaseCategory((int)args[0]);
    }
}
