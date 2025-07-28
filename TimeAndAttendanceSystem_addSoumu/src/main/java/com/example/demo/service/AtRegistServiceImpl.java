package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmpAtData;
import com.example.demo.repository.EmpAtDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtRegistServiceImpl implements AtRegistService {

	private final EmpAtDataRepository repository;
	
	@Override
	public void regist(EmpAtData ead) {

		repository.add(ead);
		
	}

}
