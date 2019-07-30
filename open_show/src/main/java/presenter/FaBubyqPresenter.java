package presenter;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/28
 * @Description：XXXX
 */
public class FaBubyqPresenter extends WDPresenter<IAppRequest> {
    public FaBubyqPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.FaBubyq((long) args[0],(String) args[1],(RequestBody) args[2]);
    }
}
