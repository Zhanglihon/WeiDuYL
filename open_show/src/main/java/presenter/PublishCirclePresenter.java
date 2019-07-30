package presenter;


import android.util.Log;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDPresenter;
import zhang.bw.com.common.core.http.IAppRequest;

/**
 * @author dingtao
 * @date 2018/12/28 11:23
 * qq:1940870847
 */
public class PublishCirclePresenter extends WDPresenter<IAppRequest>{

    public PublishCirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("sickCircleId", (String) args[2]);
        List<LocalMedia> list = (List<LocalMedia>) args[3];
        if (list.size()>1) {
            for (int i = 1; i < list.size(); i++) {
                Log.e("aaa",String.valueOf(list.get(i)));
                File file = new File(String.valueOf(list.get(i)));
                builder.addFormDataPart("picture", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/octet-stream"),
                                file));
            }
        }
        return iRequest.releaseCircle((long) args[0],(String)args[1],builder.build());

    }
}
