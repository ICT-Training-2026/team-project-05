package com.example.demo.form;

import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class OutPutForm {
	
	@NotNull
	@Size(min=6,max=6, message="正しいコードを入力してください")
	private String emp_num;
	
	// これら3つのフィールドをOutPutFormに追加してください
	private String emp_name;
	private Long over_time;
	private Long total_time;
	
	@NotNull
	@PastOrPresent(message = "過去または現在の日付を入力してください")
	private Date date;

	@Size(min=2,max=2, message="正しいコードを入力してください")
	private String At_type_code;
	
	@NotNull
	@PastOrPresent(message = "過去または現在の時間を入力してください")
	private Time st_time;
	
	@PastOrPresent(message="開始時間以降の時間を指定してください")
	private Time fn_time;
	
	@PastOrPresent(message="正しい時間を入力してください")
	private Time rest_time;

}