package me.jackson.pro21qqzone1.qqzone.pojo;

import java.sql.Date;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Date topicDate;
    private UserBasic author; //M : 1 及多个topic对应一个user

    private List<Reply> replyList; // 1 : N 一个topic拥有多个reply

    public Topic() {
    }

    public Topic(String title, String content, Date topicDate, UserBasic author) {
        this.title = title;
        this.content = content;
        this.topicDate = topicDate;
        this.author = author;
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(Date topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
