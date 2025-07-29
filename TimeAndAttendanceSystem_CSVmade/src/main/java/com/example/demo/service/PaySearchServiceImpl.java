package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmpAtDataSoumu;
import com.example.demo.repository.EmpAtDataSoumuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaySearchServiceImpl implements PaySearchService {

	private final EmpAtDataSoumuRepository repository;

	@Override
	public List<EmpAtDataSoumu> findByData(String team_code, String emp_num, Date date, String at_type_code) {

		List<EmpAtDataSoumu> list 
			= repository.selectByData(team_code, emp_num, date, at_type_code);
	
		return list;
	}

}
