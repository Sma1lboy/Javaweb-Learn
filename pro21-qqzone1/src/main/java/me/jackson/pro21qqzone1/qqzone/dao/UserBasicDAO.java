package me.jackson.pro21qqzone1.qqzone.dao;

import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public interface UserBasicDAO {
    //get spicific user by id and password
    public UserBasic getUserBasic(Connection conn, String loginId, String pwd);
    //get spicific user by id
    public UserBasic getUserBasic(Connection conn, Integer id);
    //loading friends get all friends
    public List<UserBasic> getUsersBasicList(Connection conn,UserBasic userBasic);


}
