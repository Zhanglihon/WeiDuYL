package presenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/23
 * @Description：XXXX
 */
public class ChaFabiaoPresenter extends WDPresenter<IAppRequest>{
    public ChaFabiaoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.Chabypl((String)args[0],(String)args[1],(String)args[2]);
    }
}
