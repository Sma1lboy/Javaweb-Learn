package me.jackson.pro21qqzone1.qqzone.controller;

import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.HostReplyService;
import me.jackson.pro21qqzone1.qqzone.service.ReplyService;
import me.jackson.pro21qqzone1.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public class TopicController {
    private TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session) {
        //get clicked topic
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);
        return "frames/detail";

    }
    public String delTopic(Integer topicId) {
        topicService.delTopicById(topicId);


        return "redirect:topic.do?operate=getTopicList";
    }
    public String getTopicList(HttpSession session) {
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //数据库中的topiclist和session中的topiclist不同 所以需要重新设置
        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);

        session.setAttribute("friend", userBasic);
        //因为只需要重新加载小块区域 不需要重新加载一整块
        return "frames/main";

    }
    public String addNewTopic(String title, String content, Integer userId) {
        topicService.addTopic(new UserBasic(userId), new Topic(title, content, new Date(new java.util.Date().getTime()), new UserBasic(userId)));

        return "redirect:topic.do?operate=getTopicList";
    }
}
