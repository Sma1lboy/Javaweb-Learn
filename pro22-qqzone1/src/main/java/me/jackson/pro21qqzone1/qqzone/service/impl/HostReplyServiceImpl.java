package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.HostReplyDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;
import me.jackson.pro21qqzone1.qqzone.pojo.Reply;
import me.jackson.pro21qqzone1.qqzone.service.HostReplyService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public class HostReplyServiceImpl implements HostReplyService {
    HostReplyDAO hostReplyDAO = null;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        Connection conn = null;
        HostReply hostReply = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            hostReply = hostReplyDAO.getHostReplyByReplyId(conn, replyId);

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
        return hostReply;
    }

    @Override
    public void delHostReplyById(Integer hostReplyId) {
        try {
            hostReplyDAO.delHostReplyById(JDBCUtils.getConnection(), hostReplyId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
