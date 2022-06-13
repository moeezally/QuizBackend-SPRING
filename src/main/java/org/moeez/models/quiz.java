package org.moeez.models;

import java.util.List;

public class quiz {

    private int id;
    private String name;
    private List<question> questions;
    private user created_by;
    private boolean is_Active;
    public int attempts;

    public quiz(int id, String name, user created_by, boolean is_Active) {
        this.id = id;
        this.name = name;
        this.created_by = created_by;
        this.is_Active = is_Active;
    }

    public quiz(int id, String name, List<question> questions, user created_by, int attempts, boolean is_Active) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.created_by = created_by;
        this.is_Active = is_Active;
        this.attempts=attempts;
    }

    public quiz(String name, List<question> questions, user created_by, int attempts, boolean is_Active) {
        this.name = name;
        this.questions = questions;
        this.created_by = created_by;
        this.attempts=attempts;
        this.is_Active = is_Active;
    }

    public quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<question> questions) {
        this.questions = questions;
    }

    public user getCreated_by() {
        return created_by;
    }

    public void setCreated_by(user created_by) {
        this.created_by = created_by;
    }

    public boolean isIs_Active() {
        return is_Active;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                ", created_by=" + created_by +
                ", is_Active=" + is_Active +
                '}';
    }
}
