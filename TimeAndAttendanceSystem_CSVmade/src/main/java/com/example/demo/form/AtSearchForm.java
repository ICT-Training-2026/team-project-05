package com.example.demo.form;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AtSearchForm {
	
	private String emp_num;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	private String at_type_code;
	
}

