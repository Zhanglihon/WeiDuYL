package zhang.bw.com.open_my.presenter;

import android.util.Log;

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
public class ShouXixunPresenter extends WDPresenter<IAppRequest> {
    public ShouXixunPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findUserInfoCollectionList((long)args[0],(String)args[1],(String)args[2],(String)args[3]);
    }
}
