package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.EmpAtData;

public interface AtSearchService {

	//List<EmpAtData> findByNumber(String emp_num);
	List<EmpAtData> findByData(String emp_num, Date date, String st_type_code);
	
}

