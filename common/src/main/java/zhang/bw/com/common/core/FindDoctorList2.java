package zhang.bw.com.common.core;

import io.reactivex.Observable;
import zhang.bw.com.common.core.http.IAppRequest;

public class FindDoctorList2 extends WDPresenter<IAppRequest> {
    public FindDoctorList2(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return  iRequest.findDoctorList2((long) args[0],(String)args[1],(String)args[2],(String)args[3]
                ,(String)args[4],(String)args[5],(String)args[6]);
    }
}
