package me.jackson.pro21qqzone1.qqzone.dao;

import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;

import java.sql.Connection;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public interface HostReplyDAO {

    HostReply getHostReplyByReplyId(Connection conn, Integer replyId);

    void delHostReplyById(Connection conn, Integer hostReplyId);
}
