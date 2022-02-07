package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcUtil {

    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        JdbcUtil.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void commit(Connection connection) throws SQLException {
        if (connection != null) {
            connection.commit();
            System.out.println("JdbcUtils.commit: Commit!");
        } else {
            System.out.println("JdbcUtils.commit: connection is null!");
        }
    }

    public static void rollback(Connection connection) throws SQLException {
        if (connection != null) {
            connection.rollback();
            System.out.println("JdbcUtils.rollback: Rollback!");
        } else {
            System.out.println("JdbcUtils.rollback: connection is null!");
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
