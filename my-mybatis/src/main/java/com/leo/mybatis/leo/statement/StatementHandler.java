package com.leo.mybatis.leo.statement;

import com.leo.mybatis.leo.config.LeoConfiguration;
import com.leo.mybatis.leo.config.MapperRegistry;
import com.leo.mybatis.leo.result.ResultSetHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库操作和结果处理分发
 * Created by LSH7120 on 2019/2/27.
 */
public class StatementHandler {
    private LeoConfiguration configuration;
    private final ResultSetHandler resultSetHandler;

    public StatementHandler(LeoConfiguration configuration) {
        this.configuration = configuration;
        this.resultSetHandler = new ResultSetHandler(configuration);
    }

    public <T> T query(MapperRegistry.MapperData mapperData, Object parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {
            //JDBC
            Connection conn = getConnection();
            //TODO ParameterHandler
            PreparedStatement pstmt = conn.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();
            //ResultSetHandler
            return (T)resultSetHandler.handle(pstmt, mapperData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        String driver="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/leo?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username="root";
        String password="123456";
        Connection conn=null;
        try {
            Class.forName(driver);//classLoader,加载对应驱动
            conn= DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
