package zhang.bw.com.common.bean;

/**
 * @author dingtao
 * @date 2018/12/28 10:05
 * qq:1940870847
 */
public class Result<T> {
     String status;
     String message;
     T result;
     String headPath;
     String prepayId;
     String partnerId;
     String nonceStr;
     String timeStamp;
     String sign;
     String appId;
     String packageValue;

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

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

    public Result(String status, String message, T result, String headPath, String prepayId, String partnerId, String nonceStr, String timeStamp, String sign, String appId, String packageValue) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.headPath = headPath;
        this.prepayId = prepayId;
        this.partnerId = partnerId;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;
        this.appId = appId;
        this.packageValue = packageValue;
    }
}
