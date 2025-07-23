package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpInfo {

	private int emp_num; //社員番号
	private String emp_name; //社員名
	private int team_code; //所属コード
	private int pto_date; //有休日数

}
