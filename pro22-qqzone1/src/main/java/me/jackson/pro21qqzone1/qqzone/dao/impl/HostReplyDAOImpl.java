package me.jackson.pro21qqzone1.qqzone.dao.impl;

import me.jackson.pro21qqzone1.myssm.base.BaseDAO;
import me.jackson.pro21qqzone1.qqzone.dao.HostReplyDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;

import java.sql.Connection;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    //get Host Reply By Reply id
    @Override
    public HostReply getHostReplyByReplyId(Connection conn, Integer replyId) {
        String url = "select id, content, host_reply_date \"hostReplyDate\", author, reply from t_host_reply where reply=?";
        return getInstance(conn, url, replyId);
    }

    @Override
    public void delHostReplyById(Connection conn, Integer hostReplyId) {

        update(conn, "delete from t_host_reply where id = ?", hostReplyId);
    }
}
