package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.UserBasicDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.UserBasicService;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO = null;
    //login只管登陆 做登陆验证
    @Override
    public UserBasic login(String loginId, String pwd) {
        //1.登陆验证
        try {
            Connection conn = JDBCUtils.getConnection();
            return userBasicDAO.getUserBasic(conn, loginId, pwd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserBasic> getFriendsList(UserBasic userBasic) {
        try {
            Connection conn = JDBCUtils.getConnection();
            //只有id
            List<UserBasic> friendList = userBasicDAO.getUsersBasicList(conn, userBasic);
            for (int i = 0; i < friendList.size(); i++) {
                UserBasic user = friendList.get(i);
                user = userBasicDAO.getUserBasic(conn, user.getId());
                friendList.set(i, user);
            }
            return friendList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
