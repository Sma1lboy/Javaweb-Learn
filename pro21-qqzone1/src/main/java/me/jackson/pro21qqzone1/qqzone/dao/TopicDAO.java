package me.jackson.pro21qqzone1.qqzone.dao;

import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public interface TopicDAO {
    //get spicific user all the topic
    List<Topic> getTopics(Connection conn, UserBasic userBasic);
    //add topic
    void addTopic(Connection conn, UserBasic userBasic, Topic topic);

    void delTopic( Connection conn, Topic topic);

    Topic getTopic(Connection conn, Integer id);
}
