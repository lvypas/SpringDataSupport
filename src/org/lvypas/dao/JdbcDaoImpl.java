package org.lvypas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.lvypas.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {
    @Autowired
    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Circle getCircle(int circleId){
        
        Connection conn = null;
        Circle circle = null;
        
        try {
            
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
            ps.setInt(1, circleId);            
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name1"));
            }
            
            rs.close();
            ps.close();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                
            }            
        }        
        return circle;
    }

}
