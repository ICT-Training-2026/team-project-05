package com.example.demo.repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmpAtData;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpAtDataRepositoryImpl implements EmpAtDataRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(EmpAtData ead) {

		String sql =
				" INSERT INTO emp_at_data " +
				" (emp_num, date, at_type_code, st_time, fn_time, rest_time, over_time) " +
				" VALUES (?, ?, ?, ?, ?) ";	

		jdbcTemplate.update(sql, 
				ead.getEmp_num(),
				ead.getDate(),
				ead.getAt_type_code(),
				ead.getSt_time(),
				ead.getFn_time(),
				ead.getRest_time(),
				ead.getOver_time()
				);
		
	}

	@Override
	public List<EmpAtData> selectByNumber(int emp_num) {

		String sql = 
				" SELECT                                                 " + 
				"   ead.at_type_code,                                        " +
				"   ead.st_time,                                             " +
				"   ead.fn_time,                                             " +
				"   ead.rest_time,                                           " +
				"   ead.over_time                                            " +
				" FROM                                                   " +
				"  	emp_at_data ead                                      " +
				" LEFT OUTER JOIN                                        " +
				"  	at_type att ON ead.at_type_code = att.at_type_code   " +
				" WHERE                                                  " +
				"  	ead.emp_num = ?                                      " +
				" ORDER BY                                               " +
				"   ead.emp_num;                                         ";

		int p = emp_num;	// プレースホルダの値
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, p);
		
		// 値の取得⇒結果の格納
		List<EmpAtData> result = new ArrayList<EmpAtData>(); // 結果の初期化
		for (Map<String, Object> one : list) {
			EmpAtData ead = new EmpAtData();
			ead.setSt_time((Time)one.get("st_time"));
			ead.setFn_time((Time)one.get("fn_time"));
			ead.setRest_time((Time)one.get("rest_time"));
			ead.setOver_time((int)one.get("over_time"));
			result.add(ead);
		}

		return result;
	}

	@Override
	public void update(EmpAtData ead) {

		String sql =
				" UPDATE                      " + 
				"   emp_at_data               " + 
				" SET                         " + 
				"   date = ? ,                " + 
				"   st_time = ? ,             " +
				"   fn_time = ? ,             " +
				"   rest_time = ? ,           " +
				"   over_time = ?             " +
				" WHERE                       " + 
				"   emp_num = ? AND date = ? ";
		
		jdbcTemplate.update(sql, 
							ead.getEmp_num(),
							ead.getSt_time(),
							ead.getFn_time(),
							ead.getRest_time(),
							ead.getOver_time(),
							ead.getEmp_num(),
							ead.getDate()
				);
		
	}

	@Override
	public void delete(EmpAtData ead) {

		String sql =
				" DELETE              " + 
				" FROM                " + 
				"   emp_at_data       " + 
				" WHERE               " + 
				"   emp_num = ? AND date = ?"; 

		jdbcTemplate.update(sql,ead.getEmp_num(), ead.getDate());
		
	}

}