package org.moeez.models;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class Connection {

    private final String url = "jdbc:mysql://localhost:3306/quizzes";
    private final String dbUsername = "root";
    private final String dbPassword = "moeez_99";
    private java.sql.Connection con;
    private Statement st;


    @Bean
    public Statement makeStatement() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setCon(DriverManager.getConnection(getUrl(), getDbUsername(), getDbPassword()));
        setSt(getCon().createStatement());
        return st;
    }

    public java.sql.Connection getCon() {
        return con;
    }

    public void setCon(java.sql.Connection con) {
        this.con = con;
    }

    public void closeCon() throws SQLException {
        this.con.close();
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public String getUrl() {
        return url;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

}
