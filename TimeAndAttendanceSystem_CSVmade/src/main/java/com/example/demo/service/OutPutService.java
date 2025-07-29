package com.example.demo.service;

import java.io.PrintWriter;
import java.util.List;

import com.example.demo.entity.EmpAtDataSoumu;

public interface OutPutService {

	void outputCsv(List<EmpAtDataSoumu> data, PrintWriter eriter);

}
