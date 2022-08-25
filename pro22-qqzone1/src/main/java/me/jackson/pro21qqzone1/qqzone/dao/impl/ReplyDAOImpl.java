package me.jackson.pro21qqzone1.qqzone.dao.impl;

import me.jackson.pro21qqzone1.myssm.base.BaseDAO;
import me.jackson.pro21qqzone1.qqzone.dao.ReplyDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;

import java.sql.Connection;
import java.time.Instant;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplysById(Connection conn, Integer id) {
        String url = "select id, content, reply_date \"replyDate\", author, topic from t_reply where topic = ?";
        List<Reply> list = getForList(conn, url, id);
        return list;
    }

    @Override
    public List<Reply> getReplys(Connection conn, Topic topic) {
        return null;
    }

    @Override
    public void addReply(Connection conn, Reply reply) {
        String url = "insert into t_reply(content, reply_date, author, topic) values(?,?,?,?)";
        update(conn, url, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());

    }

    @Override
    public void delReplyById(Connection conn, Integer Id) {
        update(conn, "delete from t_reply where id=?", Id);
    }

    @Override
    public Reply getReplyById(Connection conn, Integer id) {
        return getInstance(conn, "select id, content, reply_date \"replyDate\", author, topic from t_reply where id= ?", id);
    }
}
