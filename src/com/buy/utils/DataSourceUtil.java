package com.buy.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.xml.stream.events.Comment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {
    private  static final String driver="com.mysql.jdbc.Driver";
    private  static final String url="jdbc:mysql://localhost:3306/easybuy?useUnicode=true&characterEncoding=utf-8";
    private  static final String userName="root";
    private  static final String password="root";
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
        Connection conn=null;
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
    //关闭连接的方法
    public static void closeConnection(Connection conn)
    {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

