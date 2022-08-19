package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.ReplyDAO;
import me.jackson.pro21qqzone1.qqzone.dao.impl.ReplyDAOImpl;
import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.service.HostReplyService;
import me.jackson.pro21qqzone1.qqzone.service.ReplyService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO = null;
    //应用的是其他POJO对应的service接口 而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的 我需要调用别人的业务逻辑方法 而不要去深入考虑别人内部的细节
    private HostReplyService hostReplyService = null;

    @Override
    public List<Reply> getReplys( Integer topicId) {
            Connection conn = null;
            List<Reply> replyList = null;
            try {
                conn = JDBCUtils.getConnection();
                conn.setAutoCommit(false);

                replyList = replyDAO.getReplysById(conn, topicId);
                for (Reply reply : replyList) {
                    HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
                    reply.setHostReply(hostReply);
                }


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
            return replyList;
    }
}
