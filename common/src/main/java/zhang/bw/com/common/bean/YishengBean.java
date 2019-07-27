package zhang.bw.com.common.bean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/17
 * @Description：XXXX
 */
public class YishengBean {
    private int doctorId;
    private String doctorName;
    private String imagePic;
    private String jobTitle;
    private String inauguralHospital;
    private double praise;
    private double serverNum;
    private double servicePrice;
    private double praiseNum;
    private double badNum;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getInauguralHospital() {
        return inauguralHospital;
    }

    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }

    public double getPraise() {
        return praise;
    }

    public void setPraise(double praise) {
        this.praise = praise;
    }

    public double getServerNum() {
        return serverNum;
    }

    public void setServerNum(double serverNum) {
        this.serverNum = serverNum;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public double getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(double praiseNum) {
        this.praiseNum = praiseNum;
    }

    public double getBadNum() {
        return badNum;
    }

    public void setBadNum(double badNum) {
        this.badNum = badNum;
    }

    public YishengBean(int doctorId, String doctorName, String imagePic, String jobTitle, String inauguralHospital, double praise, double serverNum, double servicePrice, double praiseNum, double badNum) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.imagePic = imagePic;
        this.jobTitle = jobTitle;
        this.inauguralHospital = inauguralHospital;
        this.praise = praise;
        this.serverNum = serverNum;
        this.servicePrice = servicePrice;
        this.praiseNum = praiseNum;
        this.badNum = badNum;
    }
}
