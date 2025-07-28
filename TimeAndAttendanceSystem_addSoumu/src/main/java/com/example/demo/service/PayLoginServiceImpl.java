package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.EmpAuthSoumuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayLoginServiceImpl implements PayLoginService {

	private final EmpAuthSoumuRepository repository;
	
	@Override
	public boolean findByEmpNumAndPass(String emp_num,String emp_pass){
		boolean result = repository.selectByEmpNum(emp_num,emp_pass);
		return result;
	}

//	@Override
//	public EmpAuth login(String emp_num,String emp_pass) {
//		return repository.findByEmpNumAndPass(emp_num, emp_pass);
//	}

}
