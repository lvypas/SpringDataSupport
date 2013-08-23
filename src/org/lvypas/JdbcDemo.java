package org.lvypas;

import org.lvypas.dao.JdbcDaoImpl;
import org.lvypas.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring.xml");
        JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
        dao.insertCircle(new Circle(5, "Fifth Circle"));
        System.out.println(dao.getAllCircles().size());
        //dao.createTriangleTable();

    }

}
