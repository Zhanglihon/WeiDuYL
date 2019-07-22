package zhang.bw.com.open_my.presenter;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
public class MyPresenter extends WDPresenter<IAppRequest> {
    public MyPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        File arg= (File) args[2];
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("image",arg.getName(),RequestBody.create(MediaType.parse("multipart/octet-stream"),arg));
        return iRequest.modifyHeadPic((long)args[0],(String)args[1],builder.build());
    }
}
