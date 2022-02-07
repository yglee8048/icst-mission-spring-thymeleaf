package com.lgcns.icst.mission.spring.thymeleaf.hangma.order.dao;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.util.JdbcUtil;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.order.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao {

    public void insertOrder(Connection connection, Integer empNo, Integer price, Integer discountPrice) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO HANGMA_ORDER(ORDER_ID, EMP_NO, PRICE, DISCOUNT_PRICE, ORDER_DT) " +
                    "VALUES((SELECT NVL(MAX(ORDER_ID), 0) + 1 FROM HANGMA_ORDER), ?, ?, ?, SYSDATE)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empNo);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, discountPrice);

            int result = preparedStatement.executeUpdate();
            if (result != 1) {
                throw new Exception("주문 생성 중 오류가 발생했습니다.");
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    public List<OrderEntity> findAllByEmpNoOrderByIdDesc(Connection connection, Integer empNo) throws Exception {
        List<OrderEntity> orderEntities = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT ORDER_ID, PRICE, DISCOUNT_PRICE, ORDER_DT" +
                    " FROM HANGMA_ORDER" +
                    " WHERE EMP_NO = ?" +
                    " ORDER BY ORDER_ID DESC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empNo);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long orderId = resultSet.getLong("ORDER_ID");
                int price = resultSet.getInt("PRICE");
                int discountPrice = resultSet.getInt("DISCOUNT_PRICE");
                Date orderDt = resultSet.getDate("ORDER_DT");

                orderEntities.add(new OrderEntity(orderId, empNo, price, discountPrice, orderDt));
            }
            return orderEntities;
        } finally {
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
    }
}
