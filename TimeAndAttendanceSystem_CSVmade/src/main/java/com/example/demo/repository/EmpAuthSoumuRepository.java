package com.example.demo.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmpAuthSoumuRepository {
    //EmpAuth findByEmpNumAndPass(String emp_num, String emp_pass);

	boolean selectByEmpNum(String emp_num, String emp_pass);
}

