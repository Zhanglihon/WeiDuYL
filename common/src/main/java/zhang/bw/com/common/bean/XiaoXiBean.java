package zhang.bw.com.common.bean;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/8/8
 * @Description：XXXX
 */
public class XiaoXiBean {
    private int notReadNum;
    private  int noticeType;

    public XiaoXiBean(int notReadNum, int noticeType) {
        this.notReadNum = notReadNum;
        this.noticeType = noticeType;
    }

    public int getNotReadNum() {
        return notReadNum;
    }

    public void setNotReadNum(int notReadNum) {
        this.notReadNum = notReadNum;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }
}
