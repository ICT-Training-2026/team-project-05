package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpAuth {

	private int emp_num; //社員番号
	private String emp_pass; //パスワード
	private int auth_code; //権限コード

}
