package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.TopicDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.ReplyService;
import me.jackson.pro21qqzone1.qqzone.service.TopicService;
import me.jackson.pro21qqzone1.qqzone.service.UserBasicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class TopicServiceImpl implements TopicService {


    private TopicDAO topicDAO = null;
    //此处调用replyService层去获取信息
    private ReplyService replyService = null;
    //此处调用userBasicService去获取完整的用户信息
    private UserBasicService userBasicService = null;

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

    @Override
    public Topic getTopicById(Integer Id) {
        Connection conn = null;
        Topic topic = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            topic = getTopic(Id);

//            topic.
            //find reply list
            List<Reply> replyList = replyService.getReplys(topic.getId());
            topic.setReplyList(replyList);


            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return topic;
    }

    @Override
    public Topic getTopic(Integer Id) {
        Connection conn = null;
        Topic topic = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            topic = topicDAO.getTopic(conn, Id);
            UserBasic user = userBasicService.getUserById(topic.getAuthor().getId());
            topic.setAuthor(user);

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return topic;
    }

    @Override
    public void delTopicById(Integer topicId) {
        try {
            Topic topic = getTopic(topicId);
            if(topic != null) {
                replyService.delReplyList(topic);
                topicDAO.delTopic(JDBCUtils.getConnection(), new Topic(topicId));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTopic(UserBasic userBasic, Topic topic) {
        try {
            topicDAO.addTopic(JDBCUtils.getConnection(), userBasic, topic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
