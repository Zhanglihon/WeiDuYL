package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindDiseaseKnowledge extends WDPresenter<IAppRequest> {
    public FindDiseaseKnowledge(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDiseaseKnowledge((String)args[0]);
    }
}
