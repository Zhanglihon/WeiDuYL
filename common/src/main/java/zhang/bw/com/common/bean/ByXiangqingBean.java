package zhang.bw.com.common.bean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/20
 * @Description：XXXX
 */
public class ByXiangqingBean {
    private int sickCircleId;
    private int authorUserId;
    private int departmentId;
    private int adoptFlag;
    private String title;
    private String department;
    private String disease;
    private String detail;
    private String treatmentHospital;
    private String treatmentStartTime;
    private String treatmentEndTime;
    private String treatmentProcess;
    private String picture;
    private int collectionFlg;
    private int collectionNum;
    private int commentNum;
    private String adoptNickName;
    private String adoptHeadPic;
    private String adoptComment;
    private long adoptTime;

    public int getSickCircleId() {
        return sickCircleId;
    }

    public void setSickCircleId(int sickCircleId) {
        this.sickCircleId = sickCircleId;
    }

    public int getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(int authorUserId) {
        this.authorUserId = authorUserId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getAdoptFlag() {
        return adoptFlag;
    }

    public void setAdoptFlag(int adoptFlag) {
        this.adoptFlag = adoptFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTreatmentHospital() {
        return treatmentHospital;
    }

    public void setTreatmentHospital(String treatmentHospital) {
        this.treatmentHospital = treatmentHospital;
    }

    public String getTreatmentStartTime() {
        return treatmentStartTime;
    }

    public void setTreatmentStartTime(String treatmentStartTime) {
        this.treatmentStartTime = treatmentStartTime;
    }

    public String getTreatmentEndTime() {
        return treatmentEndTime;
    }

    public void setTreatmentEndTime(String treatmentEndTime) {
        this.treatmentEndTime = treatmentEndTime;
    }

    public String getTreatmentProcess() {
        return treatmentProcess;
    }

    public void setTreatmentProcess(String treatmentProcess) {
        this.treatmentProcess = treatmentProcess;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCollectionFlg() {
        return collectionFlg;
    }

    public void setCollectionFlg(int collectionFlg) {
        this.collectionFlg = collectionFlg;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getAdoptNickName() {
        return adoptNickName;
    }

    public void setAdoptNickName(String adoptNickName) {
        this.adoptNickName = adoptNickName;
    }

    public String getAdoptHeadPic() {
        return adoptHeadPic;
    }

    public void setAdoptHeadPic(String adoptHeadPic) {
        this.adoptHeadPic = adoptHeadPic;
    }

    public String getAdoptComment() {
        return adoptComment;
    }

    public void setAdoptComment(String adoptComment) {
        this.adoptComment = adoptComment;
    }

    public long getAdoptTime() {
        return adoptTime;
    }

    public void setAdoptTime(long adoptTime) {
        this.adoptTime = adoptTime;
    }

    public ByXiangqingBean(int sickCircleId, int authorUserId, int departmentId, int adoptFlag, String title, String department, String disease, String detail, String treatmentHospital, String treatmentStartTime, String treatmentEndTime, String treatmentProcess, String picture, int collectionFlg, int collectionNum, int commentNum, String adoptNickName, String adoptHeadPic, String adoptComment, long adoptTime) {
        this.sickCircleId = sickCircleId;
        this.authorUserId = authorUserId;
        this.departmentId = departmentId;
        this.adoptFlag = adoptFlag;
        this.title = title;
        this.department = department;
        this.disease = disease;
        this.detail = detail;
        this.treatmentHospital = treatmentHospital;
        this.treatmentStartTime = treatmentStartTime;
        this.treatmentEndTime = treatmentEndTime;
        this.treatmentProcess = treatmentProcess;
        this.picture = picture;
        this.collectionFlg = collectionFlg;
        this.collectionNum = collectionNum;
        this.commentNum = commentNum;
        this.adoptNickName = adoptNickName;
        this.adoptHeadPic = adoptHeadPic;
        this.adoptComment = adoptComment;
        this.adoptTime = adoptTime;
    }
}
