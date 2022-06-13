package org.moeez.models;

import java.util.Arrays;
import java.util.Date;

public class attempt {

    private int id;
    private quiz quiz_id;
    private user user_id;
    private String status;
    private int score;
    private String[] answers;
    private String taken_on;

    public attempt(int id, quiz quiz_id, user user_id, String status, int score, String[] answers, String taken_on) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.user_id = user_id;
        this.status = status;
        this.score = score;
        this.answers = answers;
        this.taken_on = taken_on;
    }

    public attempt(quiz quiz_id, user user_id, String status, int score, String[] answers, String taken_on) {
        this.quiz_id = quiz_id;
        this.user_id = user_id;
        this.status = status;
        this.score = score;
        this.answers = answers;
        this.taken_on = taken_on;
    }

    public attempt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public quiz getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(quiz quiz_id) {
        this.quiz_id = quiz_id;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getTaken_on() {
        return taken_on;
    }

    public void setTaken_on(String taken_on) {
        this.taken_on = taken_on;
    }

    @Override
    public String toString() {
        return "attempt{" +
                "id=" + id +
                ", quiz_id=" + quiz_id +
                ", user_id=" + user_id +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", answers=" + Arrays.toString(answers) +
                ", taken_on=" + taken_on +
                '}';
    }
}
