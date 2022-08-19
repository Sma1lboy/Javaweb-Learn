package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.TopicDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.TopicService;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class TopicServiceImpl implements TopicService {

    TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        Connection conn = null;
        List<Topic> topics = null;
        try {
            conn = JDBCUtils.getConnection();
            topics = topicDAO.getTopics(conn, userBasic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return topics;
    }
}
