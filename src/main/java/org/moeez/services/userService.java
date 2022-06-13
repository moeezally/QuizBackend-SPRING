package org.moeez.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.moeez.dao.userDao;
import org.moeez.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class userService {

    @Autowired
    userDao userDao;


    public String authenticate(String email, String password) {
        String authToken = null;
        if(userDao.validate(email,password)){
            user User= userDao.findbyEmail(email);
            System.out.println(User);
            Claims claims = Jwts.claims();
            claims.put("id", User.getId());
            claims.put("email",User.getEmail());
            claims.put("dateTime", new Date());
            claims.put("type",User.getType());
            authToken = Jwts.builder()
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, userDao.JWT_KEY)
                    .setClaims(claims)
                    .compact();
            System.out.println(authToken);
        }
        return authToken;
    }

    public boolean createStudent(user User) throws Exception {
        user UserExists=userDao.findbyEmail(User.getEmail());

        if(UserExists!=null){
            throw new Exception("user exists");
        }
        User.setType("Student");
        if(User.getPassword().isEmpty()||User.getPassword().equals(""))
        User.setPassword("123456");
        return userDao.create(User);
    }

    public boolean createTeacher(user User) throws Exception {
        user UserExists=userDao.findbyEmail(User.getEmail());

        if(UserExists!=null){
            throw new Exception("user exists");
        }
        User.setType("Teacher");
        return userDao.create(User);
    }

    public boolean update(user User){
        return userDao.update(User);
    }

    public user findById(int id){
        return userDao.findbyId(id);
    }

    public user findByEmail(String email) {
        return userDao.findbyEmail(email);
    }

    public boolean delete(int id) {
        return userDao.delete(id);
    }

    public List<user> findStudents(){
        return userDao.findUsersByType("Student");
    }

    public List<user> findTeachers(){
        return userDao.findUsersByType("Teacher");
    }

    public List<user> findAll(){
        return userDao.findAll();
    }

    public user compareAndModifyUser(user Original,user Update){
        if(Update.getName()==null||Update.getName().isEmpty()){
            Update.setName(Original.getName());
        }
        if(Update.getEmail()==null||Update.getEmail().isEmpty()){
            Update.setName(Original.getEmail());
        }
        if(Update.getPassword()==null||Update.getPassword().isEmpty()){
            Update.setName(Original.getPassword());
        }
        return Update;
    }

}
