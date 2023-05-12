package com.hzlx.ch01.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatchTest {
    public static void main(String[] args) throws Exception {
         final String URL = "jdbc:mysql://localhost:3306/itcast?useSSL=false&useUniCode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
         final String USERNAME="root";
         final String PASSWORD="123456";


         long time=System.currentTimeMillis();
         Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        String sql = "insert into t_test_info value(null,?,default)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1,"张三"+i);
            preparedStatement.addBatch();
            if (i%1000==0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();

        long enftime=System.currentTimeMillis();
        System.out.println("耗时"+(enftime-time));
    }
}
