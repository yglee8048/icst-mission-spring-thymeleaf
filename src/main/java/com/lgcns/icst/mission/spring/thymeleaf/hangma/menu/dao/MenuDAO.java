package com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.dao;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.util.JdbcUtil;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.entity.Category;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuDAO {

    public List<MenuEntity> findAll(Connection connection) throws SQLException {
        List<MenuEntity> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT MENU_ID, CATEGORY, MENU_NM, PRICE, IMG_FILE_NM FROM HANGMA_MENU";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long menuId = resultSet.getLong("MENU_ID");
                String category = resultSet.getString("CATEGORY");
                String menuNm = resultSet.getString("MENU_NM");
                int price = resultSet.getInt("PRICE");
                String imgFileNm = resultSet.getString("IMG_FILE_NM");

                result.add(new MenuEntity(menuId, category, menuNm, price, imgFileNm));
            }
            return result;
        } finally {
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
    }

    public MenuEntity findById(Connection connection, Long menuId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT CATEGORY, MENU_NM, PRICE, IMG_FILE_NM FROM HANGMA_MENU WHERE MENU_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, menuId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String category = resultSet.getString("CATEGORY");
                String menuNm = resultSet.getString("MENU_NM");
                int price = resultSet.getInt("PRICE");
                String imgFileNm = resultSet.getString("IMG_FILE_NM");

                return new MenuEntity(menuId, category, menuNm, price, imgFileNm);
            }
            return null;
        } finally {
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
    }

    public void save(Connection connection, Category category, String menuNm, int price) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO HANGMA_MENU VALUES((SELECT MAX(MENU_ID) + 1 FROM HANGMA_MENU), ?, ?, ?, 'americano.jpg')";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.name());
            preparedStatement.setString(2, menuNm);
            preparedStatement.setInt(3, price);

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new Exception("메뉴 추가 중 오류가 발생했습니다.");
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    public void update(Connection connection, MenuEntity menuEntity) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE HANGMA_MENU SET CATEGORY = ?, MENU_NM = ?, PRICE = ? WHERE MENU_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, menuEntity.getCategory().name());
            preparedStatement.setString(2, menuEntity.getMenuNm());
            preparedStatement.setInt(3, menuEntity.getPrice());
            preparedStatement.setLong(4, menuEntity.getMenuId());

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new Exception("메뉴 수정 중 오류가 발생했습니다.");
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    public void deleteById(Connection connection, long menuId) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM HANGMA_MENU WHERE MENU_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, menuId);

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new Exception("메뉴 삭제 중 오류가 발생했습니다.");
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }
}
