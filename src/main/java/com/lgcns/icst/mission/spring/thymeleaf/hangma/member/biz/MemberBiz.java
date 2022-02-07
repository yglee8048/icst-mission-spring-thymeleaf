package com.lgcns.icst.mission.spring.thymeleaf.hangma.member.biz;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.util.JdbcUtil;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.dao.EmployeeDAO;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class MemberBiz {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public MemberBiz(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public EmployeeEntity findByEmpNo(Integer empNo) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            EmployeeEntity employeeEntity = employeeDAO.findByEmpNo(connection, empNo);
            if (employeeEntity == null) {
                throw new Exception("일치하는 사번이 존재하지 않습니다.");
            }
            return employeeEntity;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void signUp(Integer empNo, String empNm) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            employeeDAO.save(connection, empNo, empNm);
            JdbcUtil.commit(connection);
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void updateEmployee(Integer empNo, String empNm) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            employeeDAO.update(connection, empNo, empNm);

            JdbcUtil.commit(connection);
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void withdraw(Integer empNo) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            employeeDAO.deleteByEmpNo(connection, empNo);

            JdbcUtil.commit(connection);
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
