package me.jackson.pro21qqzone1.qqzone.dao.impl;

import me.jackson.pro21qqzone1.myssm.base.BaseDAO;
import me.jackson.pro21qqzone1.qqzone.dao.TopicDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {

    @Override
    public List<Topic> getTopics(Connection conn, UserBasic userBasic) {
        int id = userBasic.getId();
        //delete author from here
        List<Topic> list = getForList(conn, "select id, title, content, topic_date \"topicDate\", author from t_topic where author = ? order by id", id);
        return list;
    }

    @Override
    public void addTopic(Connection conn, UserBasic userBasic, Topic topic) {

    }

    @Override
    public void delTopic(Connection conn, Topic topic) {

    }

    @Override
    public Topic getTopic(Connection conn, Integer id) {
        String url = "select id, title, content, topic_date \"topicDate\", author from t_topic where id=?";
        return getInstance(conn, url, id);
    }
}
