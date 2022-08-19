package me.jackson.pro21qqzone1.qqzone.dao.impl;

import me.jackson.pro21qqzone1.myssm.base.BaseDAO;
import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;
import me.jackson.pro21qqzone1.qqzone.dao.UserBasicDAO;
import me.jackson.pro21qqzone1.qqzone.pojo.UserBasic;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {


    @Override
    public UserBasic getUserBasic(Connection conn, String loginId, String pwd) {
        String url = "select id, login_id \"loginId\", nickname, pwd, head_img \"headImg\" from t_user_basic where login_id = ? and pwd = ?";
        return getInstance(conn, url, loginId, pwd);
    }

    @Override
    public UserBasic getUserBasic(Connection conn, Integer id) {
        String url = "select id, login_id \"loginId\", nickname, pwd, head_img \"headImg\" from t_user_basic where id = ?";
        return getInstance(conn, url, id);
    }

    @Override
    public List<UserBasic> getUsersBasicList(Connection conn, UserBasic userBasic) {
        String url = "select fid \"id\" from t_friend where uid = ?";
        return getForList(conn, url, userBasic.getId());
    }

    @Test
    public void test() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        UserBasic user = getUserBasic(conn, "u002", "ok");
        System.out.println(user);
        List<UserBasic> list = getUsersBasicList(conn, user);
        list.forEach(System.out::println);

    }
}
