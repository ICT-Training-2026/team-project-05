package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtType {

	private int at_type_code; //勤怠区分コード
	private String at_type_name; //勤怠区分名

}
