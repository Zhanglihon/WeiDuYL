package zhang.bw.com.common.core.http;



import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import zhang.bw.com.common.bean.BannerBean;
import zhang.bw.com.common.bean.ByXiangqingBean;
import zhang.bw.com.common.bean.BingBean;
import zhang.bw.com.common.bean.Byliebiao;
import zhang.bw.com.common.bean.HbchaXun;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.GameBean;
import zhang.bw.com.common.bean.JanBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.bean.MyjiankangBean;
import zhang.bw.com.common.bean.NameBean;
import zhang.bw.com.common.bean.PingBean;
import zhang.bw.com.common.bean.PingLunBean;
import zhang.bw.com.common.bean.PriceBean;
import zhang.bw.com.common.bean.Result;
import zhang.bw.com.common.bean.ShouziBean;
import zhang.bw.com.common.bean.ShowBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import zhang.bw.com.common.bean.WddaBean;
import zhang.bw.com.common.bean.WdscVideoBean;
import zhang.bw.com.common.bean.YaoBean;
import zhang.bw.com.common.bean.YishengBean;
import zhang.bw.com.common.bean.ZixunBean;

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
    @GET("share/information/v1/findInformationList")
    Observable<Result<List<JanBean>>>findDrugsKnowledgeList1(@Query("plateId")String plateId, @Query("page")String page, @Query("count")String count);
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<BingBean>>>findDiseaseCategory(@Query("departmentId")String departmentId);
    @GET("user/video/v1/findVideoCategoryList")
    Observable<Result<List<NameBean>>>findVideoCategoryList();
    @GET("user/video/v1/findVideoVoList")
    Observable<Result<List<GameBean>>>findVideoVoList(@Header("userId")long userId, @Header("sessionId")String sessionId, @Query("categoryId")String categoryId, @Query("page")String page, @Query("count")String count);
    @GET("user/video/v1/findVideoCommentList")
    Observable<Result<List<PingBean>>>findVideoCommentList(@Query("videoId")String videoId);
    @FormUrlEncoded
    @POST("user/video/verify/v1/addUserVideoCollection")
    Observable<Result>addUserVideoCollection(@Header("userId")long userId, @Header("sessionId")String sessionId,@Field("videoId")String videoId);
    @GET("user/verify/v1/findUserWallet")
    Observable<Result>findUserWallet1(@Header("userId")long userId, @Header("sessionId")String sessionId);
    @FormUrlEncoded
    @POST("user/video/verify/v1/videoBuy")
    Observable<Result>videoBuy(@Header("userId")long userId, @Header("sessionId")String sessionId,@Field("videoId")String videoId,@Field("price")int price);
    @FormUrlEncoded
    @POST("user/video/verify/v1/addVideoComment")
    Observable<Result>addVideoComment(@Header("userId")long userId, @Header("sessionId")String sessionId,@Field("videoId")String videoId,@Field("content")String content);
   @GET("share/knowledgeBase/v1/findDrugsKnowledge")
   Observable<Result<XiangBean>>findDrugsKnowledge(@Query("id")String id);
   @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
   Observable<Result<BingZeng>>findDiseaseKnowledge(@Query("id")String id);
   @GET("user/inquiry/v1/findDoctorInfo")
   Observable<Result<ZixunBean>>findDoctorInfo(@Header("userId")long userId, @Header("sessionId")String sessionId, @Query("doctorId")String doctorId );
   @GET("share/information/v1/findInformation")
   Observable<Result<QingBean>>findInformation(@Header("userId")long userId, @Header("sessionId")String sessionId, @Query("infoId")String infoId);
   @GET("share/v1/homePageSearch")
   Observable<Result<ShoucuoBean>>homePageSearch(@Query("keyWord")String keyWord);
    //发送邮箱验证码
    @FormUrlEncoded
    @POST("user/v1/sendOutEmailCode")
    Observable<Result> sendOutEmailCode(@Field("email")String email);
    @FormUrlEncoded
    @POST("user/inquiry/verify/v1/followDoctor")
    Observable<Result>followDoctor(@Header("userId")long userId,@Header("sessionId")String sessionId,@Field("doctorId")String doctorId);

    @DELETE("user/inquiry/verify/v1/cancelFollow")
    Observable<Result>cancelFollow(@Header("userId")long userId,@Header("sessionId")String sessionId,@Query("doctorId") String doctorId);
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
    @POST("user/verify/v1/addSign")
    Observable<Result> addSign(@Header("userId") long userId,
                               @Header("sessionId") String sessionId);
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
        //请求H币信息
    @GET("user/verify/v1/findHealthyCurrencyNoticeList")
    Observable<Result<List<HbchaXun>>> Hchaxun(@Header("userId") long id, @Header("sessionId") String sessionId,
                                               @Query("page") String page, @Query("count") String count);

    //病友圈请求详情
    @GET("user/sickCircle/v1/findSickCircleInfo")
    Observable<Result<ByXiangqingBean>> Byxiangqing(@Header("userId") long id, @Header("sessionId") String sessionId,
                                                          @Query("sickCircleId") String sickCircleId);
    //病友圈评论列表
    @GET("user/sickCircle/v1/findSickCircleCommentList")
    Observable<Result<List<PingLunBean>>> PingLunlb(@Header("userId") long id, @Header("sessionId") String sessionId,
                                                    @Query("sickCircleId") String sickCircleId, @Query("page") String page,
                                                    @Query("count") String count);

    //修改密码
    @PUT("user/verify/v1/updateUserPwd")
    Observable<Result> updateUserPwd(@Header("userId")long userId,
                                     @Header("sessionId")String sessionId,
                                     @Query("oldPwd")String oldPwd,
                                     @Query("newPwd")String newPwd);
    //用户查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<Result<WddaBean>> findUserArchives(@Header("userId") long userId,
                                                  @Header("sessionId") String sessionId);
    //添加用户档案
    @POST("user/verify/v1/addUserArchives")
    Observable<Result> addUserArchives(@Header("userId") long userId,
                                       @Header("sessionId") String sessionId,
                                       @Body RequestBody body);
    //完善用户信息
    @PUT("user/verify/v1/perfectUserInfo")
    Observable<Result> perfectUserInfo(@Header("userId") long userId,
                                       @Header("sessionId") String sessionId,
                                       @Query("height")String height,
                                       @Query("weight")String weight,
                                       @Query("age")String age);
    //查询用户当天是否签到
    @GET("user/verify/v1/whetherSignToday")
    Observable<Result<Integer>> whetherSignToday(@Header("userId")long userId,
                                                      @Header("sessionId")String sessionId);
    //用户收藏健康课堂视频列表
    @GET("user/verify/v1/findVideoCollectionList")
    Observable<Result<List<WdscVideoBean>>> findVideoCollectionList(@Header("userId")long userId,
                                                              @Header("sessionId")String sessionId,
                                                              @Query("page")int page,
                                                              @Query("count")int count);
    //病友圈评论列表
    @POST("user/sickCircle/verify/v1/publishComment")
    Observable<Result> FaBiaopl(@Header("userId") long id, @Header("sessionId") String sessionId,
                                                    @Query("sickCircleId") String sickCircleId, @Query("content") String content);

    //查询病友圈人发表的病友圈意见
    @GET("user/sickCircle/v1/findPatientSickCircleList")
    Observable<Result<List<Byliebiao>>> Chabypl(@Query("patientUserId") String patientUserId, @Query("page") String page,
                               @Query("count") String count);

    //发布病友圈
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("user/sickCircle/verify/v1/publishSickCircle")
    Observable<Result<Integer>> FaBubyq(@Header("userId") long id, @Header("sessionId") String sessionId,
                                        @Body RequestBody body);

    //上传图片
    @POST("user/sickCircle/verify/v1/uploadSickCirclePicture")
    Observable<Result> releaseCircle(@Header("userId") long id, @Header("sessionId") String sessionId, @Body MultipartBody body);
}
