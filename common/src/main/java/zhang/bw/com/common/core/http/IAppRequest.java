package zhang.bw.com.common.core.http;



import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import zhang.bw.com.common.bean.BannerBean;
import zhang.bw.com.common.bean.BingBean;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.JanBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.bean.ShouziBean;
import zhang.bw.com.common.bean.ShowBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.bean.TxscBean;
import zhang.bw.com.common.bean.YaoBean;

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
    //头像上传
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result<String>> modifyHeadPic(@Header("userId")long userId,
                                               @Header("sessionId")String sessionId,
                                               @Body MultipartBody body);
    //根据用户ID查询用户信息
    @GET("user/verify/v1/getUserInfoById")
    Observable<Result<CXBean>> getUserInfoById(@Header("userId") long userId,
                                               @Header("sessionId") String sessionId);
    //检验验证码
    @FormUrlEncoded
    @POST("user/v1/checkCode")
    Observable<Result> checkCode(@Field("email")String email,
                                 @Field("code")String code);
    //重置用户密码（忘记密码用）
    @PUT("user/v1/resetUserPwd")
    Observable<Result> resetUserPwd(@Query("email") String email,
                                    @Query("pwd1") String pwd1,
                                    @Query("pwd2")String pwd2);
    //用户签到
    @FormUrlEncoded
    @POST("user/verify/v1/addSign")
    Observable<Result> addSign(@Field("userId")long userId,
                               @Field("sessionId")String sessionId);
    //修改用户昵称
    @FormUrlEncoded
    @PUT("user/verify/v1/modifyNickName")
    Observable<Result> modifyNickName(@Header("userId") long userId,
                                      @Header("sessionId") String sessionId,
                                      @Field("nickName") String nickName);
    //修改用户性别
    @FormUrlEncoded
    @PUT("user/verify/v1/updateUserSex")
    Observable<Result> updateUserSex(@Header("userId")long userId,
                                     @Header("sessionId")String sessionId,
                                     @Field("sex")String sex);
    //查询用户资讯收藏列表
    @GET("user/verify/v1/findUserInfoCollectionList")
    Observable<Result<List<ShouziBean>>> findUserInfoCollectionList(@Header("userId") long userId,
                                                              @Header("sessionId") String sessionId,
                                                              @Query("page")String page,
                                                              @Query("count")String count);
}
