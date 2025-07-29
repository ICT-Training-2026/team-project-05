package com.example.demo.form;

import lombok.Data;

@Data
public class PayLoginForm {
	
	private String emp_num;
	private String emp_pass;
	private int auth_code;
	
}
