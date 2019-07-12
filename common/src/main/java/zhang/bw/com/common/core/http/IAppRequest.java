package zhang.bw.com.common.core.http;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zhang.bw.com.common.bean.BannerBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.bean.ShowBean;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {
    @GET("share/v1/bannersShow")
    Observable<Result<List<BannerBean>>>bannersShow();
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<ShowBean>>>findDepartment();
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Result<List<MyjiankangBean>>>findInformationPlateList();
    @GET("share/information/v1/findInformationList")
    Observable<Result>findInformationList(@Query("plateId")String plateId,@Query("page")String page,@Query("count")String count);

}
