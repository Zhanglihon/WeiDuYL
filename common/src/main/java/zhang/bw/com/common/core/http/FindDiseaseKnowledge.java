package zhang.bw.com.common.core.http;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;

public class FindDiseaseKnowledge extends WDPresenter<IAppRequest> {
    public FindDiseaseKnowledge(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDiseaseKnowledge((String)args[0]);
    }
}
