package com.siemens.ifa;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcIntro {
    String dbUrl = "jdbc:mysql://localhost:3306/car_rental";
    String dbUser = "root";
    String dbPassword = "root";

    public static void main(String[] args) {
        JdbcIntro intro = new JdbcIntro();
        System.out.println("System users");
        List<User> users = intro.selectAll();
        users.stream().forEach(System.out::println);

    }

    List<User> selectAll() {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            Statement statement = conn.createStatement(); //semi boilerplate
            String query = "SELECT * FROM users"; //sql string not quite ok
            ResultSet rs = statement.executeQuery(query);
            List<User> users = new ArrayList<>();
            while(rs.next()) {
                final Integer id = rs.getInt("id");
                final String name = rs.getString("name");
                final String email = rs.getString("email");
                final String phone = rs.getString("phone");
                if(phone != null) {
                    users.add(new User(id, name, email, phone));
                } else {
                    users.add(new User(id, name, email));
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Ooops");
        }
    }
}
