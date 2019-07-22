package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindInformationList1 extends WDPresenter<IAppRequest> {
    int page=0;
    public FindInformationList1(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        boolean aa = (boolean) args[1];
        if(aa){
            page=1;
        }else {
            page++;
        }
        return iRequest.findDrugsKnowledgeList1((String)args[0],String.valueOf(page),(String)args[2]);
    }

    public int getPage() {
        return page;
    }
}
