package me.jackson.pro21qqzone1.qqzone.controller;

import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.TopicService;
import me.jackson.pro21qqzone1.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class UserController {
    UserBasicService userBasicService = null;
    TopicService topicService = null;

    /**
     * @deprecated using pageController instead of using start operater to jump to login.html
     * @return
     */
    public String start() {
        return "login";
    }

    public String login(String loginId, String pwd, HttpSession session) {
        //获取用户
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        //如果用户不为空
        if(userBasic != null) {
            session.setAttribute("userBasic", userBasic);
            //friend保存的是当前进入的是谁的空间
            session.setAttribute("friend", userBasic);
            return "index";
        } else {//则登陆失败
            return "login";
        }
    }
    public String intoFriend(Integer id, HttpSession session) {
        //根据id获取指定信息

        UserBasic currFriend = userBasicService.getUserById(id);
        List<Topic> currFriendTopicList = topicService.getTopicList(currFriend);
        currFriend.setTopicList(currFriendTopicList);

        session.setAttribute("friend", currFriend);
        return "index";

    }
}
