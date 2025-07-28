package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.EmpAtDataSoumu;

public interface PaySearchService {

	//List<EmpAtData> findByNumber(String emp_num);
	List<EmpAtDataSoumu> findByData(String team_code,String emp_num, Date date, String st_type_code);
	
}

