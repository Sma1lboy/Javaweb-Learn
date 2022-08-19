package me.jackson.pro21qqzone1.qqzone.pojo;


import java.sql.Date;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/15
 */
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;

    //年月日时分秒
    private Date date;
    private String star;

    public UserDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
