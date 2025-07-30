package com.example.demo.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmpAtDataSoumu;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpAtDataSoumuRepositoryImpl implements EmpAtDataSoumuRepository {

	private final JdbcTemplate jdbcTemplate;
	@Override
	public List<EmpAtDataSoumu> selectByData(String team_code, String emp_num, Date date, String at_type_code) {
		if(emp_num == ""){
			String sql = 
					"SELECT ead.emp_num, ei.emp_name," +
					"CAST(SUM(TIME_TO_SEC(TIMEDIFF(ead.fn_time, ead.st_time))                   " +
					"    - TIME_TO_SEC(ead.rest_time)) / 3600 AS SIGNED) AS total_time,                 " +
					"CAST(SUM(GREATEST((TIME_TO_SEC(TIMEDIFF(ead.fn_time, ead.st_time))                   " +
					"    - TIME_TO_SEC(ead.rest_time)) - 7 * 3600, 0) / 3600)AS SIGNED) AS over_time                 " +
					"FROM emp_at_data ead                                                  " +
					"LEFT OUTER JOIN                                        " +
					"  	emp_info ei ON ead.emp_num = ei.emp_num   " +
					"WHERE ei.team_code = ?                                                 " +
					"ORDER BY ead.emp_num                                     ";
	
			String t = team_code;	// プレースホルダの値
			
			// SQLで検索（プレースホルダ：p）
			List<Map<String, Object>> list 
					= jdbcTemplate.queryForList(sql, t);
			
			// 値の取得⇒結果の格納
			List<EmpAtDataSoumu> result = new ArrayList<EmpAtDataSoumu>(); // 結果の初期化
			for (Map<String, Object> one : list) {
				EmpAtDataSoumu ead = new EmpAtDataSoumu();
				ead.setEmp_num((String)one.get("emp_num"));
				ead.setEmp_name((String)one.get("emp_name"));
				ead.setTotal_time((long)one.get("total_time"));
				ead.setOver_time((long)one.get("over_time"));
				result.add(ead);
			}
			return result;
		}else if( date == null && at_type_code == "") {
		    String sql = 
					" SELECT                                                 " + 
					"   ead.emp_num,                                         " +
					"   ead.date,                                            " +
					"   ead.at_type_code,                                    " +
					"   ead.st_time,                                         " +
					"   ead.fn_time,                                         " +
					"   ead.rest_time                                        " +
					" FROM                                                   " +
					"  	emp_at_data ead                                      " +
					" LEFT OUTER JOIN                                        " +
					"  	at_type att ON ead.at_type_code = att.at_type_code   " +
					" WHERE                                                  " +
					"  	ead.emp_num = ?                                      " +
					" ORDER BY                                               " +
					"   ead.emp_num;                                         ";
	
			String p = emp_num;	// プレースホルダの値
			
			// SQLで検索（プレースホルダ：p）
			List<Map<String, Object>> list 
					= jdbcTemplate.queryForList(sql, p);
			
			// 値の取得⇒結果の格納
			List<EmpAtDataSoumu> result = new ArrayList<EmpAtDataSoumu>(); // 結果の初期化
			for (Map<String, Object> one : list) {
				EmpAtDataSoumu ead = new EmpAtDataSoumu();
				ead.setEmp_num((String)one.get("emp_num"));
				ead.setDate((Date)one.get("Date"));
				ead.setAt_type_code((String)one.get("at_type_code"));
				ead.setSt_time((Time)one.get("st_time"));
				ead.setFn_time((Time)one.get("fn_time"));
				ead.setRest_time((Time)one.get("rest_time"));
				result.add(ead);
			}
			return result;
		}else if( date != null&& at_type_code == "") {
		    String sql = 
					" SELECT                                                 " + 
					"   ead.emp_num,                                         " +
					"   ead.date,                                            " +
					"   ead.at_type_code,                                    " +
					"   ead.st_time,                                         " +
					"   ead.fn_time,                                         " +
					"   ead.rest_time                                        " +
					" FROM                                                   " +
					"  	emp_at_data ead                                      " +
					" LEFT OUTER JOIN                                        " +
					"  	at_type att ON ead.at_type_code = att.at_type_code   " +
					" WHERE                                                  " +
					"  	ead.emp_num = ? AND ead.date = ?                                      " +
					" ORDER BY                                               " +
					"   ead.emp_num;                                         ";
	
			String p = emp_num;	// プレースホルダの値
			Date d = date;
			
			// SQLで検索（プレースホルダ：p）
			List<Map<String, Object>> list 
					= jdbcTemplate.queryForList(sql, p, d);
			
			// 値の取得⇒結果の格納
			List<EmpAtDataSoumu> result = new ArrayList<EmpAtDataSoumu>(); // 結果の初期化
			for (Map<String, Object> one : list) {
				EmpAtDataSoumu ead = new EmpAtDataSoumu();
				ead.setEmp_num((String)one.get("emp_num"));
				ead.setDate((Date)one.get("Date"));
				ead.setAt_type_code((String)one.get("at_type_code"));
				ead.setSt_time((Time)one.get("st_time"));
				ead.setFn_time((Time)one.get("fn_time"));
				ead.setRest_time((Time)one.get("rest_time"));
				result.add(ead);
			}
			return result;
		}else if( date == null && at_type_code != "") {
		    String sql = 
					" SELECT                                                 " + 
					"   ead.emp_num,                                         " +
					"   ead.date,                                            " +
					"   ead.at_type_code,                                    " +
					"   ead.st_time,                                         " +
					"   ead.fn_time,                                         " +
					"   ead.rest_time                                        " +
					" FROM                                                   " +
					"  	emp_at_data ead                                      " +
					" LEFT OUTER JOIN                                        " +
					"  	at_type att ON ead.at_type_code = att.at_type_code   " +
					" WHERE                                                  " +
					"  	ead.emp_num = ? AND ead.at_type_code = ?                                      " +
					" ORDER BY                                               " +
					"   ead.emp_num;                                         ";
	
			String p = emp_num;	// プレースホルダの値
			String c = at_type_code;
			
			// SQLで検索（プレースホルダ：p）
			List<Map<String, Object>> list 
					= jdbcTemplate.queryForList(sql, p, c);
			
			// 値の取得⇒結果の格納
			List<EmpAtDataSoumu> result = new ArrayList<EmpAtDataSoumu>(); // 結果の初期化
			for (Map<String, Object> one : list) {
				EmpAtDataSoumu ead = new EmpAtDataSoumu();
				ead.setEmp_num((String)one.get("emp_num"));
				ead.setDate((Date)one.get("Date"));
				ead.setAt_type_code((String)one.get("at_type_code"));
				ead.setSt_time((Time)one.get("st_time"));
				ead.setFn_time((Time)one.get("fn_time"));
				ead.setRest_time((Time)one.get("rest_time"));
				result.add(ead);
			}
			return result;
		}else{
		    String sql = 
					" SELECT                                                 " + 
					"   ead.emp_num,                                         " +
					"   ead.date,                                            " +
					"   ead.at_type_code,                                    " +
					"   ead.st_time,                                         " +
					"   ead.fn_time,                                         " +
					"   ead.rest_time                                        " +
					" FROM                                                   " +
					"  	emp_at_data ead                                      " +
					" LEFT OUTER JOIN                                        " +
					"  	at_type att ON ead.at_type_code = att.at_type_code   " +
					" WHERE                                                  " +
					"  	ead.emp_num = ? AND ead.date = ? AND ead.at_type_code = ?" +
					" ORDER BY                                               " +
					"   ead.emp_num;                                         ";
	
			String p = emp_num;	// プレースホルダの値
			Date d = date;
			String c = at_type_code;
			
			// SQLで検索（プレースホルダ：p）
			List<Map<String, Object>> list 
					= jdbcTemplate.queryForList(sql, p, d, c);
			
			// 値の取得⇒結果の格納
			List<EmpAtDataSoumu> result = new ArrayList<EmpAtDataSoumu>(); // 結果の初期化
			for (Map<String, Object> one : list) {
				EmpAtDataSoumu ead = new EmpAtDataSoumu();
				ead.setEmp_num((String)one.get("emp_num"));
				ead.setDate((Date)one.get("Date"));
				ead.setAt_type_code((String)one.get("at_type_code"));
				ead.setSt_time((Time)one.get("st_time"));
				ead.setFn_time((Time)one.get("fn_time"));
				ead.setRest_time((Time)one.get("rest_time"));
				result.add(ead);
			}
			return result;
		}
	}

}