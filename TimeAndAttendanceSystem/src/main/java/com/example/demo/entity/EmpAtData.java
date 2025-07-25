package com.example.demo.entity;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpAtData {

	private int emp_num; //社員番号
	private Date date; //日付
	private int at_type_code; //勤怠区分コード
	private Time st_time; //始業時刻
	private Time fn_time; //終業時刻
	private int awh_time; //実労働時間
	private Time rest_time; //休憩時間
	private int over_time; //累積超過時間

}
