package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.EmpAtData;

public interface EmpAtDataRepository {

	void add(EmpAtData ead);

	List<EmpAtData> selectByData(String emp_num, Date date, String at_type_code);

	void update(EmpAtData ead);

	void delete(EmpAtData ead);

}
