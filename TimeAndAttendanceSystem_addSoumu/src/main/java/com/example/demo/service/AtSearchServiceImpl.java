package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmpAtData;
import com.example.demo.repository.EmpAtDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtSearchServiceImpl implements AtSearchService {

	private final EmpAtDataRepository repository;

	@Override
	public List<EmpAtData> findByData(String emp_num, Date date, String at_type_code) {

		List<EmpAtData> list 
			= repository.selectByData(emp_num, date, at_type_code);
	
		return list;
	}

}
