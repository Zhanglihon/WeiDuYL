package zhang.bw.com.common.bean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/8/8
 * @Description：XXXX
 */
public class WxBean {
    private String prepayId;
    private String partnerId;
    private String nonceStr;
    private String timeStamp;
    private String sign;
    private String appId;
    private String packageValue;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public WxBean(String prepayId, String partnerId, String nonceStr, String timeStamp, String sign, String appId, String packageValue) {
        this.prepayId = prepayId;
        this.partnerId = partnerId;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;
        this.appId = appId;
        this.packageValue = packageValue;
    }
}
