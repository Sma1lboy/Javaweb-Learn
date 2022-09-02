package me.jackson.pro21qqzone1.qqzone.service.impl;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.UserBasicDAO;
import me.jackson.pro21qqzone1.qqzone.dao.impl.UserBasicDAOImpl;
import me.jackson.pro21qqzone1.qqzone.pojo.Topic;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import me.jackson.pro21qqzone1.qqzone.service.TopicService;
import me.jackson.pro21qqzone1.qqzone.service.UserBasicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO = null;
    private TopicService topicService = null;


    //login只管登陆 做登陆验证
    @Override
    public UserBasic login(String loginId, String pwd) {
        //1.登陆验证
        Connection conn = null;
        //something
        UserBasic user = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            //content
            user = userBasicDAO.getUserBasic(conn, loginId, pwd);

            if (user != null) {
                //获取好友列表
                List<UserBasic> friendsList = getFriendsList(user);
                //获取当前user的日志列表
                List<Topic> topicList = topicService.getTopicList(user);
                //将好友和日志列表加入到现有的user里面
                user.setFriendList(friendsList);
                user.setTopicList(topicList);
            }


            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }

        return user;
    }

    @Override
    public List<UserBasic> getFriendsList(UserBasic userBasic) {
        Connection conn = null;
        List<UserBasic> friendList = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            //只有id
            friendList = userBasicDAO.getUsersBasicList(conn, userBasic);
            for (int i = 0; i < friendList.size(); i++) {
                UserBasic user = friendList.get(i);
                user = userBasicDAO.getUserBasic(conn, user.getId());
                friendList.set(i, user);
            }

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return friendList;
    }

    @Override
    public UserBasic getUserById(Integer id) {
        Connection conn = null;
        //something
        UserBasic user = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            //content
            user = userBasicDAO.getUserBasic(conn, id);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return user;
    }
}
