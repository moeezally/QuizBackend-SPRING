package org.moeez.services;

import org.moeez.dao.attemptDao;
import org.moeez.models.attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class attemptService {

    @Autowired
    attemptDao attemptDao;

    public List<attempt> studentAttempts(int student_id){
        return attemptDao.findAttemptsByStudent(student_id);
    }

    public List<attempt> quizAttempts(int quiz_id){
        return attemptDao.findAttemptsByQuiz(quiz_id);
    }

    public boolean addAttempt(attempt Attempt){
        return attemptDao.addAttempt(Attempt);
    }

    public List<attempt> findAll(){
        return attemptDao.findAll();
    }

    public attempt findByQuizAndStudent(int student_id,int quiz_id){
        return attemptDao.findAttemptByQuizAndStudent(quiz_id,student_id);
    }


}
