package com.llw.hospital.api;

import java.util.List;
import java.util.Map;

public interface SysCommonQueryService {
    Long nextVal(String key);

    List<Map> commonQuery(String sql, Object[] param);
}
