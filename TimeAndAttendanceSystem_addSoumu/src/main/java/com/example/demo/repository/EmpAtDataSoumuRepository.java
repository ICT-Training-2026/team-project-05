package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.EmpAtDataSoumu;

public interface EmpAtDataSoumuRepository {

	List<EmpAtDataSoumu> selectByData(String team_code, String emp_num, Date date, String at_type_code);

}
