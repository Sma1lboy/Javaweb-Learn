package me.jackson.pro21qqzone1.qqzone.service;

import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public interface TopicService {
    List<Topic> getTopicList( UserBasic userBasic);

    Topic getTopicById(Integer Id);
}
