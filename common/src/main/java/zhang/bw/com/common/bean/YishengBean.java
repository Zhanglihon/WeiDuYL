package zhang.bw.com.common.bean;

import android.graphics.Color;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
public class YishengBean {
    public String doctorName;
    public String imagePic;
    public String inauguralHospital;
    public String jobTitle;
    public String praise;
    public String serverNum;
    public int servicePrice;
    public String doctorId;
    public String recordId;
    public String jiGuangPwd;
    public int textcolor=Color.BLACK;
    public YishengBean(){

    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getImagePic() {
        return imagePic;
    }

    public void setImagePic(String imagePic) {
        this.imagePic = imagePic;
    }

    public String getInauguralHospital() {
        return inauguralHospital;
    }

    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public String getServerNum() {
        return serverNum;
    }

    public void setServerNum(String serverNum) {
        this.serverNum = serverNum;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getJiGuangPwd() {
        return jiGuangPwd;
    }

    public void setJiGuangPwd(String jiGuangPwd) {
        this.jiGuangPwd = jiGuangPwd;
    }
}
