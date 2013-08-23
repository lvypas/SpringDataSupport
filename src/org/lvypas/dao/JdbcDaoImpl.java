package org.lvypas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.lvypas.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplete = new JdbcTemplate();

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplete = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplete() {
        return jdbcTemplete;
    }

    public void setJdbcTemplete(JdbcTemplate jdbcTemplete) {
        this.jdbcTemplete = jdbcTemplete;
    }

    public Circle getCircle(int circleId) {

        Connection conn = null;
        Circle circle = null;

        try {

            conn = dataSource.getConnection();
            PreparedStatement ps = conn
                    .prepareStatement("SELECT * FROM circle where id = ?");
            ps.setInt(1, circleId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name1"));
            }

            rs.close();
            ps.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }
        return circle;
    }

    public int getCircleCount() {
        String sql = "SELECT COUNT(*) FROM CIRCLE";
        return jdbcTemplete.queryForInt(sql);
    }
    
    public String getCircleName(int circleId) {
        String sql = "SELECT NAME1 FROM CIRCLE WHERE ID = ?";
        return jdbcTemplete.queryForObject(sql, new Object[] {circleId}, String.class);       
        
    }
    
    public Circle getCircleForId(int circleId) {
        String sql = "SELECT * FROM CIRCLE WHERE ID = ?";
        return jdbcTemplete.queryForObject(sql, new Object[] {circleId}, new CircleMapper());
    }
    
    public List<Circle> getAllCircles() {
        String sql = "SELECT * FROM CIRCLE";
        return jdbcTemplete.query(sql, new CircleMapper());
    }
    
    private static final class CircleMapper implements RowMapper<Circle> {

        @Override
        public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Circle circle = new Circle(resultSet.getInt("ID"), resultSet.getString("NAME1"));            
            return circle;
        }
        
    }

}
