package me.jackson.pro21qqzone1.qqzone.pojo;

import java.util.Date;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class HostReply {
    private Integer id;
    private String content;
    private Date date;
    private UserBasic author; // M : 1
    private Reply reply; // 1 : 1

    public HostReply() {
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

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
