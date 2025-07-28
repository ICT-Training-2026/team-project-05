package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.EmpAtData;

public interface EmpAtDataRepository {

	void add(EmpAtData ead);

	List<EmpAtData> selectByNumber(int emp_code);

	void update(EmpAtData ead);

	void delete(EmpAtData ead);

}
