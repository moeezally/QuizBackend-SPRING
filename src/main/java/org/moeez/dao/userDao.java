package org.moeez.dao;

import org.moeez.models.Connection;
import org.moeez.models.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class userDao {
    public static final String JWT_KEY = "!@bmis!secret*key@encoded";

    @Autowired
    Connection con;

    public user findbyEmail(String email){
        user found=null;
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from `quizzes`.`users` where email='"+email+"';";
            System.out.println(query);

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if(rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String type = rs.getString("type");
                found = new user(id, name, email, password, type);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return found;

    }

    public user findbyId(int id){
        user found=null;
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.users where id="+id+";";
            System.out.println(query);

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if(rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String type = rs.getString("type");
                found = new user(id, name, email, password, type);
            }   } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return found;

    }



    public boolean create(user User){

        boolean inserted=false;
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "INSERT INTO `quizzes`.`users`(`name`,`email`,`password`,`type`)VALUES('" + User.getName() + "','" + User.getEmail() + "','" + User.getPassword() + "','" + User.getType() + "');";
            System.out.println(query);

            // 5. Create a ResultSet
            int result = st.executeUpdate(query);
            if(result!=0){
                inserted=true;
            }

            st.close();
            con.closeCon();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return inserted;

    }

    public boolean update(user User){
        boolean updated=false;
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "UPDATE `quizzes`.`users` SET `name` = '" + User.getName() + "',`email` = '" + User.getEmail() + "',`password` = '" + User.getPassword() + "',`type` = '" + User.getType() + "' WHERE `id` = '" + User.getId() + "';";
            System.out.println(query);

            // 5. Create a ResultSet
            int result = st.executeUpdate(query);
            if(result!=0){
                updated=true;
            }

            st.close();
            con.closeCon();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return updated;

    }

    public boolean delete(int id){
        boolean deleted=false;

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "DELETE FROM `quizzes`.`users` WHERE id=" + id + ";";
            int result = st.executeUpdate(query);
            System.out.println(query);
            if(result!=0){
                deleted=true;
            }
            st.close();
            con.closeCon();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return deleted;
    }

    public List<user> findUsersByType(String type){
        List<user> Users= new ArrayList<user>();
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.users where type='"+type+"';";
            System.out.println(query);

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                String name=rs.getString("name");
                int id= rs.getInt("id");
                String password=rs.getString("password");
                String email=rs.getString("email");
                user student=new user(id,name,email,password,type);

                Users.add(student);

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return Users;
    }

    public List<user> findAll(){
        List<user> Users= new ArrayList<user>();
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.users ;";
            System.out.println(query);

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                String name=rs.getString("name");
                int id= rs.getInt("id");
                String password=rs.getString("password");
                String type=rs.getString("type");
                String email=rs.getString("email");
                user student=new user(id,name,email,password,type);

                Users.add(student);

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return Users;
    }

    public boolean validate(String email, String password){
        boolean userExist=false;
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from `quizzes`.`users` where email='" + email + "' and password='" + password + "';";
            System.out.println(query);


            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                userExist = true;
                System.out.println("Validation returned "+userExist);
            }

            // 6. Close all connections
            rs.close();
            st.close();
            con.closeCon();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return userExist;
    }


}
