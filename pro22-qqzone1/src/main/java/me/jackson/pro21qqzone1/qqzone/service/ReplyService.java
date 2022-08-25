package me.jackson.pro21qqzone1.qqzone.service;

import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public interface ReplyService {
    //get according to topic's id
    List<Reply> getReplys(Integer topicId);
    void addReply(Reply reply);

    void delReply(Integer replyId);

    void delReplyList(Topic topic);
}
