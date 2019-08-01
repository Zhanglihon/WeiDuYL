package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindInformationList1 extends WDPresenter<IAppRequest> {
    public FindInformationList1(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {


        return iRequest.findDrugsKnowledgeList1((String)args[0],(String)args[1],(String)args[2]);
    }


}
