package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.ReplyDAO;
import me.jackson.pro21qqzone1.qqzone.dao.impl.ReplyDAOImpl;
import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.HostReplyService;
import me.jackson.pro21qqzone1.qqzone.service.ReplyService;
import me.jackson.pro21qqzone1.qqzone.service.UserBasicService;

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
    private UserBasicService userBasicService = null;

    @Override
    public List<Reply> getReplys( Integer topicId) {
            Connection conn = null;
            List<Reply> replyList = null;
            try {
                conn = JDBCUtils.getConnection();
                conn.setAutoCommit(false);

                replyList = replyDAO.getReplysById(conn, topicId);
                for (Reply reply : replyList) {
                    Integer userId = reply.getAuthor().getId();
                    UserBasic user = userBasicService.getUserById(userId);
                    HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
                    reply.setAuthor(user);
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
    //fix later

    @Override
    public void addReply(Reply reply) {
        try {
            replyDAO.addReply(JDBCUtils.getConnection(), reply);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = getReplys(topic.getId());
        for (Reply reply : replyList) {
            delReply(reply.getId());
        }
    }

    @Override
    public void delReply(Integer replyId) {
        try {
            //
            //获取hostreply看存不存在
            //如果存在hostreply 直接删除reply会导致 外键报错
            Reply reply = replyDAO.getReplyById(JDBCUtils.getConnection(), replyId);
            if(reply != null) {
                if(reply.getHostReply() != null) {
                    HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getHostReply().getId());
                    if (hostReply != null) {
                        hostReplyService.delHostReplyById(hostReply.getId());
                    }
                }
                replyDAO.delReplyById(JDBCUtils.getConnection(), replyId);
            }

        } catch (Exception e ) {
            e.printStackTrace();
         }
    }
}
