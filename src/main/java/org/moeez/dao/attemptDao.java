package org.moeez.dao;

import org.moeez.models.Connection;
import org.moeez.models.attempt;
import org.moeez.models.quiz;
import org.moeez.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class attemptDao {
    @Autowired
    Connection con;

    @Autowired
    quizDao quizDao;

    @Autowired
    userDao userDao;

    public int findNumberOfQuizAttempts(int quiz_id){
        int attempts=0;
        try {
            Statement st = con.makeStatement();
            String attemptsQuery = "select count(quiz_id) as attempts from quizzes.attempts where quiz_id="+quiz_id+";";
            ResultSet attemptsRS=st.executeQuery(attemptsQuery);
            if(attemptsRS.next()){
                attempts=attemptsRS.getInt("attempts");
            }
        }
         catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return attempts;
    }

    public boolean addAttempt(attempt Attempt){
        boolean inserted=false;
        String Answers="";
        for(int i=0;i<Attempt.getAnswers().length;i++){
            Answers+=Attempt.getAnswers()[i];
        }
        System.out.println(Answers);
        try {
            Statement st = con.makeStatement();
            String attemptsQuery = "INSERT INTO `quizzes`.`attempts`(`quiz_id`,`user_id`,`status`,`score`,`answers`,`taken_on`)VALUES('"+Attempt.getQuiz_id().getId()+"','"+Attempt.getUser_id().getId()+"','"+Attempt.getStatus()+"','"+Attempt.getScore()+"','"+Answers+"','"+Attempt.getTaken_on()+"');";

            int attemptsRS=st.executeUpdate(attemptsQuery);
            if(attemptsRS!=0){
                inserted=true;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return inserted;
    }

    public List<attempt> findAttemptsByStudent(int student_id){
        List<attempt> attempts=new ArrayList<>();
        try {
            Statement st = con.makeStatement();
            String attemptsQuery = "select *  from quizzes.attempts where user_id="+student_id+";";
            ResultSet attemptsRS=st.executeQuery(attemptsQuery);
            while(attemptsRS.next()){
                int id=attemptsRS.getInt("id");
                int quiz_id=attemptsRS.getInt("quiz_id");
                String status=attemptsRS.getString("status");
                int score=attemptsRS.getInt("score");
                String[] answers=attemptsRS.getString("answers").split(",");
                String taken_on=attemptsRS.getString("taken_on");

                quiz quiz=quizDao.findbyId(quiz_id);
                user student=userDao.findbyId(student_id);

                attempt newAttempt=new attempt(id,quiz,student,status,score,answers,taken_on);
                attempts.add(newAttempt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return attempts;
    }

    public List<attempt> findAttemptsByQuiz(int quiz_id){
        List<attempt> attempts=new ArrayList<>();
        try {
            Statement st = con.makeStatement();
            String attemptsQuery = "select *  from quizzes.attempts where quiz_id="+quiz_id+";";
            ResultSet attemptsRS=st.executeQuery(attemptsQuery);
            while(attemptsRS.next()){
                int id=attemptsRS.getInt("id");
                int student_id=attemptsRS.getInt("user_id");
                String status=attemptsRS.getString("status");
                int score=attemptsRS.getInt("score");
                String[] answers=attemptsRS.getString("answers").split(",");
                String taken_on=attemptsRS.getString("taken_on");

                quiz quiz=quizDao.findbyId(quiz_id);
                user student=userDao.findbyId(student_id);

                attempt newAttempt=new attempt(id,quiz,student,status,score,answers,taken_on);
                attempts.add(newAttempt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return attempts;
    }

    public attempt findAttemptByQuizAndStudent(int quiz_id, int student_id){
        try {
            Statement st = con.makeStatement();
            String attemptsQuery = "select *  from quizzes.attempts where quiz_id='"+quiz_id+"' and user_id='"+student_id+"';";
            ResultSet attemptsRS=st.executeQuery(attemptsQuery);
            while(attemptsRS.next()){
                int id=attemptsRS.getInt("id");
                String status=attemptsRS.getString("status");
                int score=attemptsRS.getInt("score");
                String[] answers=attemptsRS.getString("answers").split(",");
                String taken_on=attemptsRS.getString("taken_on");

                quiz quiz=quizDao.findbyId(quiz_id);
                user student=userDao.findbyId(student_id);

                attempt Attempt=new attempt(id,quiz,student,status,score,answers,taken_on);
                return Attempt;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<attempt> findAll(){
        List<attempt> attempts=new ArrayList<>();
        try {
            Statement st = con.makeStatement();
            String attemptsQuery = "select *  from quizzes.attempts ;";
            ResultSet attemptsRS=st.executeQuery(attemptsQuery);
            while(attemptsRS.next()){
                int id=attemptsRS.getInt("id");
                int student_id=attemptsRS.getInt("user_id");
                int quiz_id=attemptsRS.getInt("quiz_id");
                String status=attemptsRS.getString("status");
                int score=attemptsRS.getInt("score");
                String[] answers=attemptsRS.getString("answers").split(",");
                String taken_on=attemptsRS.getString("taken_on");

                quiz quiz=quizDao.findbyId(quiz_id);
                user student=userDao.findbyId(student_id);

                attempt newAttempt=new attempt(id,quiz,student,status,score,answers,taken_on);
                attempts.add(newAttempt);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return attempts;
    }
}
