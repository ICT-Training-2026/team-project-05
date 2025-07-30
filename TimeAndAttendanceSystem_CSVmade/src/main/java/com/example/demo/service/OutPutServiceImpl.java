package com.example.demo.service;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmpAtDataSoumu;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutPutServiceImpl implements OutPutService {
	
	@Override
	public void outputCsv(List<EmpAtDataSoumu> data, PrintWriter writer) {
		for(EmpAtDataSoumu item : data) {
			writer.printf("\"%s\",\"%s\",%d,%d%n",
					item.getEmp_num(),
					item.getEmp_name(),
					item.getOver_time(),
					item.getTotal_time()
					);
		}
	}

}
