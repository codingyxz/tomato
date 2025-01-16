package com.tomato.mysql;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtilS {

    public static final String DRIVER_CLASS_STR = "driverClass";
    public static final String URL_STR = "url";
    public static final String USER_STR = "user";
    public static final String PASSWORD_STR = "password";

    public static void main(String[] args) {
        generateConn();
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection generateConn() {

        Connection conn = null;
        try {
            // 1、读取配置信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);
            String user = pros.getProperty(USER_STR);
            String password = pros.getProperty(PASSWORD_STR);
            String driverClass = pros.getProperty(DRIVER_CLASS_STR);
            String url = pros.getProperty(URL_STR);

            // 2、加载驱动
            Class.forName(driverClass);
            // 3、获取连接
            conn = DriverManager.getConnection(url, user, password);
//            System.out.println(conn);
        } catch (Exception e) {
            throw new RuntimeException("获取连接失败");
        }
        return conn;
    }


    /**
     * 关闭连接资源
     *
     * @param conn
     * @param st
     */
    public static void closeResource(Connection conn, Statement st) {
        try {
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接资源
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        closeResource(conn, st);
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
