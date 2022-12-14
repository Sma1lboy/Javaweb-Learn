package me.jackson.pro21qqzone1.qqzone.pojo;

import java.util.Date;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class Reply {
    private Integer id;
    private String content;
    private Date date;
    private UserBasic author; //M : 1
    private Topic topic; // M : 1
    private  HostReply hostReply; // 1 : 1

    public Reply() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }
}
