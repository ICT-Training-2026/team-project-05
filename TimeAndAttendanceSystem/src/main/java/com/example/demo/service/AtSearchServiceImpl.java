package com.example.demo.service;

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
	public List<EmpAtData> findByNumber(int emp_num) {

		List<EmpAtData> list 
			= repository.selectByNumber(emp_num);
	
		return list;
	}

}
