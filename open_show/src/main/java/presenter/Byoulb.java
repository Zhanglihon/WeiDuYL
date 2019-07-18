package presenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/18
 * @Description：XXXX
 */
public class Byoulb extends WDPresenter<IAppRequest>{
    public Byoulb(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.SickCircle((String) args[0],(String) args[1],(String) args[2]);
    }
}
