package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpAuthRepositoryImpl implements EmpAuthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean selectByEmpNum(String emp_num,String emp_pass) {
    	String sql = "SELECT count(*) FROM emp_auth WHERE emp_num = ? AND emp_pass = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, emp_num, emp_pass);
        return count != null && count > 0;
    }
}
