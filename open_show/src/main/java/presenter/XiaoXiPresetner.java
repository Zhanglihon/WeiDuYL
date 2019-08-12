package presenter;

import io.reactivex.Observable;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/8/8
 * @Description：XXXX
 */
public class XiaoXiPresetner extends WDPresenter<IAppRequest>{
    public XiaoXiPresetner(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.XiaoXi((long)args[0],(String) args[1]);
    }
}
