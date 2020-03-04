package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.llw.hospital.ds.mapper.SysCommonQueryMapper;
import com.llw.hospital.api.SysCommonQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value="commonService")
@Service(timeout = 2000)
public class SysCommonQueryServiceImpl implements SysCommonQueryService {

    @Autowired
    SysCommonQueryMapper sysSequenceDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long nextVal(String key) {
        return sysSequenceDao.nextVal(key);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<Map> commonQuery(String sql, Object[] param){
        List<Map> result = jdbcTemplate.query(sql, param, new RowMapper<Map>() {

            @Override
            public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                Map mapOfColValues = new HashMap();
                for (int i = 1; i <= columnCount; i++){
                    String key = rsmd.getColumnName(i);
                    Object obj = JdbcUtils.getResultSetValue(rs, i);
                    mapOfColValues.put(key, obj);
                }
                return mapOfColValues;
            }
        });
        return result;
    }
}
