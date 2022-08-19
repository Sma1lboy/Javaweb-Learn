package me.jackson.pro21qqzone1.qqzone.pojo;

import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class UserBasic {
    private Integer id;
    private String loginId;
    private String nickname;
    private String pwd;
    private String headImg;

    private UserDetail userDetail; // 1 : 1
    private List<Topic> topicList; // 1 : N
    private List<UserBasic> friendList; // M : N

    public UserBasic() {
    }

    public UserBasic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return "UserBasic{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", headImg='" + headImg + '\'' +
                ", userDetail=" + userDetail +
                ", topicList=" + topicList +
                ", friendList=" + friendList +
                '}';
    }
}
