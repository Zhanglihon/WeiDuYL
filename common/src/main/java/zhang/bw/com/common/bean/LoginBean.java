package zhang.bw.com.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
@Entity
public class LoginBean {
    @Id
    public long id;
    public String sessionId;
    public String nickName;
    public String userName;
    public String jiGuangPwd;
    public String headPic;
    public String sex;
    public String age;
    public String height;
    public String weight;
    public String email;
    public String whetherBingWeChat;
    public String invitationCode;
    public int datas=0;
    @Generated(hash = 1532007112)
    public LoginBean(long id, String sessionId, String nickName, String userName,
            String jiGuangPwd, String headPic, String sex, String age,
            String height, String weight, String email, String whetherBingWeChat,
            String invitationCode, int datas) {
        this.id = id;
        this.sessionId = sessionId;
        this.nickName = nickName;
        this.userName = userName;
        this.jiGuangPwd = jiGuangPwd;
        this.headPic = headPic;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.whetherBingWeChat = whetherBingWeChat;
        this.invitationCode = invitationCode;
        this.datas = datas;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getJiGuangPwd() {
        return this.jiGuangPwd;
    }
    public void setJiGuangPwd(String jiGuangPwd) {
        this.jiGuangPwd = jiGuangPwd;
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getHeight() {
        return this.height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWhetherBingWeChat() {
        return this.whetherBingWeChat;
    }
    public void setWhetherBingWeChat(String whetherBingWeChat) {
        this.whetherBingWeChat = whetherBingWeChat;
    }
    public String getInvitationCode() {
        return this.invitationCode;
    }
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
    public int getDatas() {
        return this.datas;
    }
    public void setDatas(int datas) {
        this.datas = datas;
    }
}
