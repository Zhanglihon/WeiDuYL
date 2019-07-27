package zhang.bw.com.open_my.presenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class WdbjPresenter extends WDPresenter<IAppRequest> {
    public WdbjPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.addUserArchives((long)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5],(String)args[6],(String)args[7],(String)args[8]);
    }
}
