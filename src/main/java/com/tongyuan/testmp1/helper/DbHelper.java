package com.tongyuan.testmp1.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

/**
 * Created by zhangcy on 2018/5/7
 */
public class DbHelper {

    private static String url = "jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static String username = "root";
    private static String password = "root";
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";


    private static final BasicDataSource DATA_SOURCE;
    private static final QueryRunner QUERY_RUNNER;
    static {

        QUERY_RUNNER = new QueryRunner();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(jdbcDriver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }

    //创建表
    public static boolean createTable(String sql){
        Connection connection = null;
        Statement stat = null;
        try{
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(url,username,password);
            stat = connection.createStatement();
            if(0==stat.executeUpdate(sql)){
                System.out.println("建表成功");
                return true;
            }else{
                System.out.println("建表失败");
            }
        }catch (Exception e){
            System.out.println("建表失败");
            e.printStackTrace();
        }finally {
            close(connection,null,stat);
        }
        return false;
    }

    /*
    插入
    return: 插入记录的主键
     */
    public Long insert(String sql){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(url,username,password);
            String [] ids = {"id"};
            ps = conn.prepareStatement(sql,ids);
            //ps.setString
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (Exception e){
            System.out.println("插入失败");
        }finally {
            close(conn,ps,null);
        }
        return (long)0;
    }

    /*
    释放资源
     */
    public static void close(Connection connection,PreparedStatement ps,Statement stat){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
