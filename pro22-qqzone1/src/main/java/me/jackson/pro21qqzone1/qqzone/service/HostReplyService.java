package me.jackson.pro21qqzone1.qqzone.service;

import me.jackson.pro21qqzone1.qqzone.pojo.HostReply;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/18
 */
public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);
    void delHostReplyById(Integer hostReplyId);
}
