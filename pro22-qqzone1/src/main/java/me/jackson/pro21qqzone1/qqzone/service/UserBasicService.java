package me.jackson.pro21qqzone1.qqzone.service;

import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public interface UserBasicService {
    UserBasic login(String loginId, String pwd);

    List<UserBasic> getFriendsList(UserBasic userBasic);
    UserBasic getUserById(Integer id);

}
