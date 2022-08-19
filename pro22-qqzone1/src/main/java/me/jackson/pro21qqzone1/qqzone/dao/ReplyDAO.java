package me.jackson.pro21qqzone1.qqzone.dao;

import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public interface ReplyDAO {
    //get spicific topic;s reply list
    List<Reply> getReplys(Connection conn, Topic topic);
    List<Reply> getReplysById(Connection conn, Integer id);

    void addReply(Topic topic, Reply reply);

    void delReplyById(Integer id);


}
