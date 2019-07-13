package zhang.bw.com.open_login.presenter;

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
public class LoginPresenter extends WDPresenter<IAppRequest> {
    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.login((String)args[0],(String)args[1]);
    }
}
