package me.jackson.pro21qqzone1.qqzone.controller;

import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.service.HostReplyService;
import me.jackson.pro21qqzone1.qqzone.service.ReplyService;
import me.jackson.pro21qqzone1.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
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
        //下面的代码上是帮助 curr topic去set它的reply list 但是这个本质上应该属于业务逻辑， 按理来说应该写在当前getTopicById业务方法中
        //already move into service method
//        //set reply to topic
//        List<Reply> replyList = replyService.getReplys(topic.getId());
//        topic.setReplyList(replyList);

        session.setAttribute("topic", topic);

        return "frames/detail";

    }
}
