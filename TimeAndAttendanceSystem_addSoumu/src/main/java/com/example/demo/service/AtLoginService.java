package com.example.demo.service;

public interface AtLoginService {
	
	//EmpAuth login(String emp_num, String emp_pass);
	boolean findByEmpNumAndPass(String emp_num, String emp_pass);
	
}

