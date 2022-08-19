package me.jackson.pro21qqzone1.myssm.base;

import me.jackson.pro21qqzone1.myssm.util.JDBCUtils;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulation the general CRUD to DB table.
 */
public abstract class BaseDAO<T> {


    private Class<T> clazz = null;

    {
        //get current baseDAO child class type
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paraType = (ParameterizedType) genericSuperclass;
        //get parent generic argument
        Type[] typeArguments = paraType.getActualTypeArguments();
        //Generic first argument
        clazz =  (Class<T>) typeArguments[0];
    }

    /**
     * general query consideration transaction with return List from db.
     * @param conn connection given by
     * @param sql sql sentence
     * @param args parameter
     * @return return a List of data you selected
     */
    public List<T> getForList(Connection conn,  String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            //fill ??
            for(int i = 0; i < args.length;i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //trying to get column
            //so therefore we have to get MetaData
            ResultSetMetaData metaData = rs.getMetaData();
            //get column through ResultSetMetaData
            while(rs.next()) {
                int cols = metaData.getColumnCount();

                T t = clazz.newInstance();
                //go through every column in same row
                for(int i = 0 ; i< cols; i++) {
                    //get the spicific data column
                    Object columnValue = rs.getObject(i + 1);
                    //获取每个列的列名字
                    String columnName = metaData.getColumnLabel(i + 1);
                    //gei fruit spicific columnName to value

                    setValue(t, columnName, columnValue);
                }
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return list;
    }

    public void setValue(Object object, String property, Object propertyValue) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class clazz = object.getClass();
        Field field = clazz.getDeclaredField(property);
        if(field != null) {
            //获取当前字段的类型名称
            String typeName = field.getType().getName();
            if(isMyType(typeName)) {
                Class myClazz = Class.forName(typeName);
//                me.jackson.pro21qqzone1.qqzone.pojo.UserBasic
                Constructor constructor = myClazz.getDeclaredConstructor(Integer.class);
                propertyValue = constructor.newInstance(propertyValue);
            }

            field.setAccessible(true);
            field.set(object, propertyValue);
        }
    }
    private boolean isNotMyType(String typeName) {
        return "java.lang.Integer".equals(typeName) || "java.lang.String".equals(typeName) || "java.util.Date".equals(typeName) || "java.sql.Date".equals(typeName);
    }
    private boolean isMyType(String typeName) {
        return !isNotMyType(typeName);
    }


// public <T> T getInstacne(){...}
    //its generic method
    //class<T> something its generic class

    /**
     *  General query consideration transaction with return one row from DB.
     * @param conn connection
     * @param sql sql sentence
     * @param args argument.
     * @return return a instance
     *
     */
    public T getInstance(Connection conn, String sql, Object... args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            //fill ??
            for(int i = 0; i < args.length;i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //trying to get column
            //so therefore we have to get MetaData
            ResultSetMetaData metaData = rs.getMetaData();
            //get column through ResultSetMetaData
            if(rs.next()) {
                int cols = metaData.getColumnCount();

                T t = clazz.newInstance();
                //go through every column in same row
                for(int i = 0 ; i< cols; i++) {
                    //get the spicific data column
                    Object columnValue = rs.getObject(i + 1);

                    //获取每个列的列名字
                    String columnName = metaData.getColumnLabel(i + 1);
                    //gei fruit spicific columnName to value
                    setValue(t, columnName, columnValue);

                }
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;

    }


    //general insert updating delete version 2.0 (考虑上事务)
    public boolean update(Connection connection, String sql, Object ...args){
        PreparedStatement ps = null;
        boolean isUpdate = false;
        try {
            //1 pre pare
            ps = connection.prepareStatement(sql);
            //2 fill ??
            for(int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //3 execute
            if(ps.executeUpdate() > 0) {
                isUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //set true for autoCommit when close the connection
            //主要针对于使用数据库连接池时
            try{
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //4 resource close
            JDBCUtils.closeResource(null, ps);
        }
        return isUpdate;
    }

    /**
     * For query some spicial value
     * @param conn
     * @param sql
     * @param args
     * @return
     * @param <E>
     */
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            //fill ?
            for (int i = 0; i < args.length;i ++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

}
