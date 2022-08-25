package me.jackson.pro21qqzone1.qqzone.controller;

import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.HostReplyService;
import me.jackson.pro21qqzone1.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.Instant;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/19
 */
public class ReplyController {

    ReplyService replyService = null;



    public String addReply(String content, Integer topicId, HttpSession session){
        Object userBasicObj = session.getAttribute("userBasic");
        UserBasic user = (UserBasic) userBasicObj;
        Reply reply = new Reply(content, new Date(new java.util.Date().getTime()), user, new Topic(topicId));
        replyService.addReply(reply);
        //detail.html
        //不能直接跳转detail.html页面 需要重新查一次
        return "redirect:topic.do?operate=topicDetail&id=" + topicId ;
    }
    public String delReply(Integer replyId, Integer topicId) {



        replyService.delReply(replyId);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId ;
    }
}
