package zhang.bw.com.common.bean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/22
 * @Description：XXXX
 */
public class PingLunBean {
    private int commentId;
    private int commentUserId;
    private String nickName;
    private String headPic;
    private String content;
    private int supportNum;
    private int opposeNum;
    private int opinion;
    private int whetherDoctor;
    private long commentTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    public int getOpposeNum() {
        return opposeNum;
    }

    public void setOpposeNum(int opposeNum) {
        this.opposeNum = opposeNum;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public int getWhetherDoctor() {
        return whetherDoctor;
    }

    public void setWhetherDoctor(int whetherDoctor) {
        this.whetherDoctor = whetherDoctor;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public PingLunBean(int commentId, int commentUserId, String nickName, String headPic, String content, int supportNum, int opposeNum, int opinion, int whetherDoctor, long commentTime) {
        this.commentId = commentId;
        this.commentUserId = commentUserId;
        this.nickName = nickName;
        this.headPic = headPic;
        this.content = content;
        this.supportNum = supportNum;
        this.opposeNum = opposeNum;
        this.opinion = opinion;
        this.whetherDoctor = whetherDoctor;
        this.commentTime = commentTime;
    }
}
