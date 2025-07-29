package com.example.demo.service;

public interface PayLoginService {
	
	//EmpAuth login(String emp_num, String emp_pass);
	boolean findByEmpNumAndPass(String emp_num, String emp_pass);
	
}

