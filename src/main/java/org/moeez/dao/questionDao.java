package org.moeez.dao;

import org.moeez.models.Connection;
import org.moeez.models.question;
import org.moeez.models.quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class questionDao {
    @Autowired
    Connection con;

    public List<question> findQuizQuestions(int quiz_id){
        List<question> questions= new ArrayList<>();

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            String query = "select * from quizzes.questions where quiz_id="+quiz_id+" ;";
            System.out.println(query);

            // 5. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                int id= rs.getInt("id");
                String statement=rs.getString("statement");
                String optionA=rs.getString("option_a");
                String optionB=rs.getString("option_b");
                String optionC=rs.getString("option_c");
                String optionD=rs.getString("option_d");
                String answer=rs.getString("answer");

                question newQuestion= new question(id,quiz_id,statement,optionA,optionB,optionC,optionD,answer);

                questions.add(newQuestion);

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return questions;
    }

    public boolean addQuestionsToQuiz(quiz Quiz){
        int insertedQuestions=0;

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            for(question q:Quiz.getQuestions()) {
                q.setQuiz_id(Quiz.getId());

                String query = "INSERT INTO `quizzes`.`questions`(`quiz_id`,`statement`,`option_a`,`option_b`,`option_c`,`option_d`,`answer`)VALUES('"+q.getQuiz_id()+"','"+q.getStatement()+"','"+q.getOptionA()+"','"+q.getOptionB()+"','"+q.getOptionC()+"','"+q.getOptionD()+"','"+q.getAnswer()+"');";
                System.out.println(query);

                // 5. Create a ResultSet
                int added = st.executeUpdate(query);
                if(added!=0) {
                    insertedQuestions++;
                }
            }


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        if(insertedQuestions==Quiz.getQuestions().size())
        return true;
        else return false;

    }

    public boolean updateQuestionsOfQuiz(quiz Quiz){
        boolean updated=false;

        try {
            Statement st = con.makeStatement();

            // 4. Create a query
            for(question q:Quiz.getQuestions()) {

                String query = "UPDATE `quizzes`.`questions` SET `statement`='"+q.getStatement()+"',`option_a`='"+q.getOptionA()+"',`option_b`='"+q.getOptionB()+"',`option_c`='"+q.getOptionC()+"',`option_d`='"+q.getOptionD()+"',`answer`='"+q.getAnswer()+"' where id="+q.getId()+";";
                System.out.println(query);

                // 5. Create a ResultSet
                ResultSet rs = st.executeQuery(query);
                if(rs.next()) {
                    updated=true;
                }
            }


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return updated;

    }
}
