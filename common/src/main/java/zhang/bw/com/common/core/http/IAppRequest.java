package zhang.bw.com.common.core.http;



import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import zhang.bw.com.common.bean.BannerBean;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.HbchaXun;
import zhang.bw.com.common.bean.BingBean;
import zhang.bw.com.common.bean.JanBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.bean.ShowBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import zhang.bw.com.common.bean.ShowBeans;
import zhang.bw.com.common.bean.YaoBean;
import zhang.bw.com.common.bean.YishengBean;

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
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Result<List<YaoBean>>>findDrugsKnowledgeList(@Query("drugsCategoryId")String drugsCategoryId, @Query("page")String page, @Query("count")String count);
    @GET("share/information/v1/findInformationList")
    Observable<Result<List<JanBean>>>findInformationList(@Query("plateId")String plateId, @Query("page")String page, @Query("count")String count);
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<BingBean>>>findDiseaseCategory(@Query("departmentId")String departmentId);
    //发送邮箱验证码
    @FormUrlEncoded
    @POST("user/v1/sendOutEmailCode")
    Observable<Result> sendOutEmailCode(@Field("email")String email);
    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> register(@Field("email")String email,
                                @Field("code")String code,
                                @Field("pwd1")String pwd1,
                                @Field("pwd2")String pwd2);
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<LoginBean>> login(@Field("email")String email,
                                        @Field("pwd")String pwd);

    //查询用户H币通知列表
    @GET("user/verify/v1/findHealthyCurrencyNoticeList")
    Observable<Result<List<HbchaXun>>> Hchaxun(@Header("userId") long id, @Header("sessionId") String sessionId,
                                         @Query("page") String page, @Query("count") String count);
    //头像上传
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result<String>> modifyHeadPic(@Header("userId")long userId,
                                               @Header("sessionId")String sessionId,
                                               @Body MultipartBody body);

    //支付
    @FormUrlEncoded
    @POST("user/verify/v1/recharge")
    Observable<Result> zhifujiekou(@Header("userId") long id, @Header("sessionId") String sessionId,
                                   @Field("money") String money, @Field("payType") String payType);

    //我的钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<Result<Integer>> Memoney(@Header("userId") long id, @Header("sessionId") String sessionId);



    //医生信息
    @GET("user/inquiry/v1/findDoctorList")
    Observable<Result> Yisheng (@Header("userId") long id, @Header("sessionId") String sessionId,
    @Query("deptId") String deptId, @Query("condition") String condition,@Query("sortBy") String sortBy,
                                                   @Query("page") String page,@Query("count") String count);

    //病友圈列表
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<Result<List<Byliebiao>>> SickCircle(@Query("departmentId") String departmentId,
                                                   @Query("page") String page,@Query("count") String coun);

        //关键字搜索病友圈
    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<Result<List<Byliebiao>>> guanjianzi(@Query("keyWord") String keyWord);

}
