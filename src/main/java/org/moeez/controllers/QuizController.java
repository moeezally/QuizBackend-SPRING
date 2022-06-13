package org.moeez.controllers;

import org.moeez.configuration.ErrorMessage;
import org.moeez.models.quiz;
import org.moeez.services.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @Autowired
    quizService quizService;

    @GetMapping
    public ResponseEntity getAllQuizzes(){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                data.put("quizzes",quizService.findAll());
                return ResponseFactory.success(data,"All Quizzes",null);

            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @GetMapping("/active")
    public ResponseEntity getActiveQuizzes(){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                data.put("quizzes",quizService.findActive());
                return ResponseFactory.success(data,"Active Quizzes",null);

            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }

        return ResponseFactory.error(data,"Error Occured",errors);
    }


    @GetMapping("/teacher/{id}")
    public ResponseEntity getQuizzesByTeacher(@PathVariable("id") int id){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                data.put("quizzes",quizService.findByTeacher(id));
                return ResponseFactory.success(data,"Teacher Quizzes",null);

            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuiz(@PathVariable("id") int id){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                data.put("quiz",quizService.findById(id));
                return ResponseFactory.success(data,"Quiz",null);

            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @PostMapping
    public ResponseEntity addQuiz(@Valid @RequestBody quiz Quiz){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            boolean inserted=quizService.addQuiz(Quiz);
            if(inserted){
            data.put("quiz",quizService.findByName(Quiz.getName()));
            return ResponseFactory.success(data,"Quiz",null);}

        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuiz(@PathVariable("id") int id){
        List errors = new ArrayList();
        try {
            boolean deleted=quizService.deleteQuiz(id);
            if(deleted){
                return ResponseFactory.success(null,"Quiz Deleted Successfully",null);}

        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }
        return ResponseFactory.error(null,"Error Occured",errors);
    }

    @PutMapping()
    public ResponseEntity updateQuiz(@RequestBody quiz Quiz){
        List errors = new ArrayList();
        HashMap data = new HashMap();
        try {
            boolean updated=quizService.updateQuiz(Quiz);
            if(updated){
                data.put("quiz",quizService.findById(Quiz.getId()));
                return ResponseFactory.success(data,"Quiz updated Successfully",null);}

        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }
        return ResponseFactory.error(null,"Error Occured",errors);
    }
}
