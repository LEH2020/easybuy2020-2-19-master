package com.buy.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;

public class DataSourceUtil {
    private  static final String driver="com.mysql.jdbc.Driver";
    private  static final String url="jdbc:mysql://localhost:3306/easybuy?useUnicode=true&characterEncoding=utf-8";
    private  static final String userName="root";
    private  static final String password="root";
    private  static Connection conn = null;
    private  static PreparedStatement pstmt = null;
    private  static  ResultSet resultSet = null;



    //创建druid数据库
    private  static DruidDataSource druidDataSource=null;
    static{
        try{
            init();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //配置druid数据
    public static DruidDataSource init() throws SQLException
    {
        //实例化DruidDataSource
        druidDataSource=new DruidDataSource();
        //设置属性的值
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        //设置连接池相关属性
        druidDataSource.setInitialSize(5);//最大化连接池数量
        druidDataSource.setMaxActive(100);//最大连接数
        druidDataSource.setMinIdle(1);//最大空闲连接数
        druidDataSource.setMaxWait(1000);//连接等待时长，单位：毫秒
        druidDataSource.setFilters("stat");//设置监控
        return druidDataSource;
    }
    /**
     * 连接数据源的方法
     * @return 连接对象
     */
    public static Connection getConn(){
        //加载mysql驱动（开启服务）
        try {
            Class.forName(driver);
            //如果数据库处于没有连接状态，则创建一个连接
            if (conn == null) {
                conn = druidDataSource.getConnection(userName,password);
                System.out.println("数据库连接成功。");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 通用增删改
     */
    public int executeUp(String sql, Object... param) {
        /* Long id = 0L; */
        int r = 0;
        try {
            conn = this.getConn();
            pstmt = conn.prepareStatement(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject((i + 1), param[i]);
                }
            }
            r = pstmt.executeUpdate();
            /*
             * rs = ps.getGeneratedKeys(); if (rs.next()) { id = rs.getLong(1);
             * System.out.println("数据主键：" + id); }
             */
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn, pstmt, resultSet);
        }
        return r;
    }


    /**
     * 释放资源的方法
     * @param rs 结果集对象
     * @param conn 数据库连接对象
     * @param pstmt 执行SQL的对象
     */
    public static void closeAll(ResultSet rs, Connection conn,PreparedStatement pstmt){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeQuery(String preparedSql, Object... param) throws ClassNotFoundException, SQLException {
        try {
            getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql);// 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            resultSet = pstmt.executeQuery(); // 执行SQL语句
        } catch (SQLException e) {
            e.printStackTrace();// 处理SQLException异常
        }
        return resultSet;
    }

    //关闭连接的方法
    public static void closeConnection(Connection c, PreparedStatement ps, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executInsert(String sql, Object...params){
        Long num =0L;
        try {
            getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);// 得到PreparedStatement对象
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]); // 为预编译sql设置参数
                }
            }
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys(); // 执行SQL语句
            if (rs.next()){
                num=rs.getLong(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();// 处理SQLException异常
        }finally {
            closeAll(null, conn, pstmt);
        }
        return resultSet;
    }
}

