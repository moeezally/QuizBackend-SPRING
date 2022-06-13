package org.moeez.controllers;

import org.moeez.configuration.ErrorMessage;
import org.moeez.models.attempt;
import org.moeez.services.attemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/attempt")
public class AttemptController {

    @Autowired
    attemptService attemptService;

    @GetMapping
    public ResponseEntity getAttempts(){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            data.put("attempts",attemptService.findAll());
            return ResponseFactory.success(data,"All Attempts",null);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity getStudentAttempts(@PathVariable("id") int id){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            data.put("attempts",attemptService.studentAttempts(id));
            return ResponseFactory.success(data,"Student Attempts",null);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity getQuizAttempts(@PathVariable("id") int id){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            data.put("attempts",attemptService.quizAttempts(id));
            return ResponseFactory.success(data,"Quiz Attempts",null);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @PostMapping
    public ResponseEntity addAttempts(@RequestBody attempt Attempt){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            boolean added=attemptService.addAttempt(Attempt);
            if(added){
            data.put("attempt",attemptService.findByQuizAndStudent(Attempt.getUser_id().getId(),Attempt.getQuiz_id().getId()));
            return ResponseFactory.success(data,"All Attempts",null);}
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }
}
