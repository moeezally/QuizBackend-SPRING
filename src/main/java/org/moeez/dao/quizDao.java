package org.moeez.dao;

import org.moeez.models.Connection;
import org.moeez.models.question;
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
public class quizDao {

    @Autowired
    Connection con;

    @Autowired
    userDao userDao;

    @Autowired
    questionDao questionDao;

    @Autowired
    attemptDao attemptDao;

    public List<quiz> findAll() {
        List<quiz> quizzes = new ArrayList<>();

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.quiz ;";

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                int created_by = rs.getInt("created_by");
                boolean is_active = rs.getBoolean("is_active");
                int attempts=attemptDao.findNumberOfQuizAttempts(id);

                user Teacher = userDao.findbyId(created_by);
                List<question> questions = questionDao.findQuizQuestions(id);
                quiz newQuiz = new quiz(id, name, questions, Teacher,attempts , is_active);
                quizzes.add(newQuiz);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return quizzes;
    }

    public List<quiz> findActive() {
        List<quiz> quizzes = new ArrayList<>();

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.quiz where is_active='1';";

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                int created_by = rs.getInt("created_by");
                boolean is_active = rs.getBoolean("is_active");
                int attempts=attemptDao.findNumberOfQuizAttempts(id);

                user Teacher = userDao.findbyId(created_by);
                List<question> questions = questionDao.findQuizQuestions(id);
                quiz newQuiz = new quiz(id, name, questions, Teacher,attempts, is_active);
                quizzes.add(newQuiz);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return quizzes;
    }

    public List<quiz> findbyTeacher(int teacher_id) {
        List<quiz> quizzes = new ArrayList<>();

        try {
            Statement st = con.makeStatement();
            user Teacher = userDao.findbyId(teacher_id);

            // 4. Create a query
            String query = "select * from quizzes.quiz where created_by='" + teacher_id + "';";

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                boolean is_active = rs.getBoolean("is_active");
                int attempts=attemptDao.findNumberOfQuizAttempts(id);

                List<question> questions = questionDao.findQuizQuestions(id);
                quiz newQuiz = new quiz(id, name, questions, Teacher,attempts, is_active);
                quizzes.add(newQuiz);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return quizzes;
    }


    public quiz findbyId(int quiz_id) {

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.quiz where id='" + quiz_id + "';";

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                boolean is_active = rs.getBoolean("is_active");
                int created_by = rs.getInt("created_by");
                user Teacher = userDao.findbyId(created_by);
                int attempts=attemptDao.findNumberOfQuizAttempts(id);


                List<question> questions = questionDao.findQuizQuestions(id);
                quiz newQuiz = new quiz(id, name, questions, Teacher,attempts , is_active);
                return newQuiz;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    public quiz findbyName(String quiz_name) {

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.quiz where name='" + quiz_name + "';";

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                boolean is_active = rs.getBoolean("is_active");
                int created_by = rs.getInt("created_by");
                user Teacher = userDao.findbyId(created_by);
                int attempts=attemptDao.findNumberOfQuizAttempts(id);


                List<question> questions = questionDao.findQuizQuestions(id);
                quiz newQuiz = new quiz(id, name, questions, Teacher, attempts,is_active);
                return newQuiz;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    public int findQuizIdByName(String quiz_name) {

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select id from quizzes.quiz where name='" + quiz_name + "';";

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt("id");

                return id;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return 0;
    }

    public boolean addQuiz(quiz Quiz) {
        boolean inserted=false;

        try {
            Statement st = con.makeStatement();
            int active=0;
            // 4. Create a query
            if(Quiz.isIs_Active()){
                active=1;
            }
            String query = "Insert Into `quizzes`.`quiz` (`name`,`created_by`,`is_active`) Values ('"+Quiz.getName()+"','"+Quiz.getCreated_by().getId()+"','"+active+"') ";
            int added = st.executeUpdate(query);

            if(added!=0) {
                int quiz_id = findQuizIdByName(Quiz.getName());

                Quiz.setId(quiz_id);
                inserted=true;
                if(!Quiz.getQuestions().isEmpty()){
                inserted=questionDao.addQuestionsToQuiz(Quiz);
                }
            }

            // 5. Create a ResultSet
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inserted;
    }


    public boolean updateQuiz(quiz Quiz) {
        boolean updated=false;

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "UPDATE `quizzes`.`quiz` SET `name`='"+Quiz.getName()+"', `is_active`='"+Quiz.isIs_Active()+"' where id="+Quiz.getId()+"; ";
            int rs = st.executeUpdate(query);

            if(rs!=0) {
                int quiz_id = findQuizIdByName(Quiz.getName());

                Quiz.setId(quiz_id);

                updated=questionDao.addQuestionsToQuiz(Quiz);
            }

            // 5. Create a ResultSet
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean deleteQuiz(int quiz_id) {

        boolean deleted=false;
        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "DELETE from quizzes.quiz where id='" + quiz_id + "';";

            // 5. Create a ResultSet
            int rs = st.executeUpdate(query);

            if (rs!=0) {
               deleted=true;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return deleted;
    }




}
