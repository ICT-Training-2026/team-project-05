package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.EmpAtData;

public interface AtSearchService {

	List<EmpAtData> findByNumber(int emp_num);
	
}

