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
            //获取好友列表
            List<UserBasic> friendsList = userBasicService.getFriendsList(userBasic);
            //获取当前user的日志列表
            List<Topic> topicList = topicService.getTopicList(userBasic);
            //将好友和日志列表加入到现有的user里面
            userBasic.setFriendList(friendsList);
            userBasic.setTopicList(topicList);
            //当user登陆账户后 整个session都是基于这个user 所以要传到会话作用域而不是请求作用域
            session.setAttribute("userBasic", userBasic);
            return "index";
        } else {//则登陆失败
            return "login";
        }
    }
}
