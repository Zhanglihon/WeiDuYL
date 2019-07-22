package zhang.bw.com.common.bean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/18
 * @Description：XXXX
 */
public class Byliebiao {
    private int sickCircleId;
    private int userId;
    private String title;
    private String detail;
    private long releaseTime;
    private int collectionNum;
    private int commentNum;
    private int amount;

    public int getSickCircleId() {
        return sickCircleId;
    }

    public void setSickCircleId(int sickCircleId) {
        this.sickCircleId = sickCircleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Byliebiao(int sickCircleId, int userId, String title, String detail, long releaseTime, int collectionNum, int commentNum, int amount) {
        this.sickCircleId = sickCircleId;
        this.userId = userId;
        this.title = title;
        this.detail = detail;
        this.releaseTime = releaseTime;
        this.collectionNum = collectionNum;
        this.commentNum = commentNum;
        this.amount = amount;
    }
}
