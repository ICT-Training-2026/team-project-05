package com.example.demo.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.entity.EmpAtDataSoumu;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpAtDataSoumuRepositoryImpl implements EmpAtDataSoumuRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpAtDataSoumu> selectByData(String team_code, String emp_num, Date date, String at_type_code) {

        // すべて空またはnullなら空リスト返す（エラー防止）
        if (!StringUtils.hasText(team_code) && !StringUtils.hasText(emp_num)
                && date == null && !StringUtils.hasText(at_type_code)) {
            return new ArrayList<>();
        }

        // emp_numが空(null or empty)ならteam_codeで集計
        if (!StringUtils.hasText(emp_num)) {
            String sql = 
                "SELECT ead.emp_num, ei.emp_name," +
                "CAST(SUM(TIME_TO_SEC(TIMEDIFF(ead.fn_time, ead.st_time)) - TIME_TO_SEC(ead.rest_time)) / 3600 AS SIGNED) AS total_time," +
                "CAST(SUM(GREATEST((TIME_TO_SEC(TIMEDIFF(ead.fn_time, ead.st_time)) - TIME_TO_SEC(ead.rest_time)) - 7 * 3600, 0) / 3600) AS SIGNED) AS over_time " +
                "FROM emp_at_data ead " +
                "LEFT OUTER JOIN emp_info ei ON ead.emp_num = ei.emp_num " +
                "WHERE ei.team_code = ? " +
                "ORDER BY ead.emp_num";

            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, team_code);

            List<EmpAtDataSoumu> result = new ArrayList<>();
            for (Map<String, Object> one : list) {
                EmpAtDataSoumu ead = new EmpAtDataSoumu();
                ead.setEmp_num((String) one.get("emp_num"));
                ead.setEmp_name((String) one.get("emp_name"));
                ead.setTotal_time(((Number) one.get("total_time")).longValue());
                ead.setOver_time(((Number) one.get("over_time")).longValue());
                result.add(ead);
            }
            return result;
        }

        // emp_num はある場合、date と at_type_code の有無で分岐

        // date=null & at_type_code empty
        if (date == null && !StringUtils.hasText(at_type_code)) {
            String sql =
                "SELECT ead.emp_num, ead.date, ead.at_type_code, ead.st_time, ead.fn_time, ead.rest_time " +
                "FROM emp_at_data ead " +
                "LEFT OUTER JOIN at_type att ON ead.at_type_code = att.at_type_code " +
                "WHERE ead.emp_num = ? " +
                "ORDER BY ead.emp_num";

            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, emp_num);

            return mapToEmpAtDataSoumuList(list);
        }

        // date != null & at_type_code empty
        if (date != null && !StringUtils.hasText(at_type_code)) {
            String sql =
                "SELECT ead.emp_num, ead.date, ead.at_type_code, ead.st_time, ead.fn_time, ead.rest_time " +
                "FROM emp_at_data ead " +
                "LEFT OUTER JOIN at_type att ON ead.at_type_code = att.at_type_code " +
                "WHERE ead.emp_num = ? AND ead.date = ? " +
                "ORDER BY ead.emp_num";

            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, emp_num, date);

            return mapToEmpAtDataSoumuList(list);
        }

        // date == null & at_type_code not empty
        if (date == null && StringUtils.hasText(at_type_code)) {
            String sql =
                "SELECT ead.emp_num, ead.date, ead.at_type_code, ead.st_time, ead.fn_time, ead.rest_time " +
                "FROM emp_at_data ead " +
                "LEFT OUTER JOIN at_type att ON ead.at_type_code = att.at_type_code " +
                "WHERE ead.emp_num = ? AND ead.at_type_code = ? " +
                "ORDER BY ead.emp_num";

            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, emp_num, at_type_code);

            return mapToEmpAtDataSoumuList(list);
        }

        // date != null & at_type_code not empty
        {
            String sql =
                "SELECT ead.emp_num, ead.date, ead.at_type_code, ead.st_time, ead.fn_time, ead.rest_time " +
                "FROM emp_at_data ead " +
                "LEFT OUTER JOIN at_type att ON ead.at_type_code = att.at_type_code " +
                "WHERE ead.emp_num = ? AND ead.date = ? AND ead.at_type_code = ? " +
                "ORDER BY ead.emp_num";

            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, emp_num, date, at_type_code);

            return mapToEmpAtDataSoumuList(list);
        }
    }

    // ヘルパーメソッド：MapからEmpAtDataSoumuリストに変換
    private List<EmpAtDataSoumu> mapToEmpAtDataSoumuList(List<Map<String, Object>> list) {
        List<EmpAtDataSoumu> result = new ArrayList<>();
        for (Map<String, Object> one : list) {
            EmpAtDataSoumu ead = new EmpAtDataSoumu();
            ead.setEmp_num((String) one.get("emp_num"));
            ead.setDate((Date) one.get("date")); // keyは小文字date
            ead.setAt_type_code((String) one.get("at_type_code"));
            ead.setSt_time((Time) one.get("st_time"));
            ead.setFn_time((Time) one.get("fn_time"));
            ead.setRest_time((Time) one.get("rest_time"));
            result.add(ead);
        }
        return result;
    }
}
