package org.lvypas.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {
        
    public int getCircleCount() {
        String sql = "SELECT COUNT(*) FROM CIRCLE";
        return this.getJdbcTemplate().queryForInt(sql);
    }
}
