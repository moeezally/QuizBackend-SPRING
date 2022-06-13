package org.moeez.services;

import org.moeez.dao.quizDao;
import org.moeez.models.quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class quizService {
    @Autowired
    quizDao quizDao;

    public List<quiz> findAll(){
        return quizDao.findAll();
    }

    public List<quiz> findActive(){
        return quizDao.findActive();
    }

    public List<quiz> findByTeacher(int teacher_id){
        return quizDao.findbyTeacher(teacher_id);
    }

    public quiz findById(int quiz_id){
        return quizDao.findbyId(quiz_id);
    }

    public quiz findByName(String quiz_name){
        return quizDao.findbyName(quiz_name);
    }

    public boolean addQuiz(quiz Quiz){
        return quizDao.addQuiz(Quiz);
    }

    public boolean deleteQuiz(int quiz_id){
        return quizDao.deleteQuiz(quiz_id);
    }

    public boolean updateQuiz(quiz Quiz){
        return quizDao.updateQuiz(Quiz);
    }

}
