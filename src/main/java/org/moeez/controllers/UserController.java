package org.moeez.controllers;

import org.moeez.configuration.ErrorMessage;
import org.moeez.models.user;
import org.moeez.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    userService UserService;

    @GetMapping
    public ResponseEntity getUsers(){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            data.put("users",UserService.findAll());
            return ResponseFactory.success(data,"All Users",null);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @GetMapping("/students")
    public ResponseEntity getStudents(){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            data.put("students",UserService.findStudents());
            return ResponseFactory.success(data,"All Students",null);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getStudent(@PathVariable("id") int id){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                data.put("student",UserService.findById(id));
                return ResponseFactory.success(data,"Student",null);
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }

        return ResponseFactory.error(data,"Error Occured",errors);
    }


    @PostMapping("/students/")
    public ResponseEntity addStudent(@Valid @RequestBody user Student){
        List errors = new ArrayList();
        HashMap data = new HashMap();

        try {
            boolean inserted=UserService.createStudent(Student);
            if(inserted){
                data.put("student",UserService.findByEmail(Student.getEmail()));
                return ResponseFactory.success(data,"Added Student",null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }
        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity updateStudent(@PathVariable("id") int id,@Valid @RequestBody user updateStudent){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                user originalStudent=UserService.findById(id);
                user UpdatedUser = UserService.compareAndModifyUser(originalStudent,updateStudent);
                boolean updated=UserService.update(UpdatedUser);
                if(updated){
                    data.put("student",UserService.findByEmail(UpdatedUser.getEmail()));
                    return ResponseFactory.success(data,"Updated Student",null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }

        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int id){
        List errors = new ArrayList();

            try {
                boolean deleted=UserService.delete(id);
                if(deleted){
                    return ResponseFactory.success(null,"Deleted Student",null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }
        return ResponseFactory.error(null,"Error Occured",errors);
    }



    @GetMapping("/teachers")
    public ResponseEntity getTeachers(){
        List errors = new ArrayList();
        HashMap data = new HashMap();
        try {
            data.put("teachers",UserService.findTeachers());
            return ResponseFactory.success(data,"All Teachers",null);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error", e.getMessage()));
        }
        return ResponseFactory.error(data,"Error Occured",errors);
    }


    @GetMapping("/teachers/{id}")
    public ResponseEntity getTeacher(@PathVariable("id") int id){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                data.put("teacher",UserService.findById(id));
                return ResponseFactory.success(data,"Teacher",null);
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }
        return ResponseFactory.error(data,"Error Occured",errors);
    }


    @DeleteMapping("/teachers/{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") int id){
        List errors = new ArrayList();

            try {
                boolean deleted=UserService.delete(id);
                if(deleted){
                    return ResponseFactory.success(null,"Deleted Teacher",null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }
        return ResponseFactory.error(null,"Error Occured",errors);
    }

    @PostMapping("/teachers/")
    public ResponseEntity addTeacher(@Valid @RequestBody user Teacher){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                boolean inserted=UserService.createTeacher(Teacher);
                if(inserted){
                    data.put("teacher",UserService.findByEmail(Teacher.getEmail()));
                    return ResponseFactory.success(data,"Added Teacher",null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }
        return ResponseFactory.error(data,"Error Occured",errors);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity updateTeacher(@PathVariable("id") int id,@Valid @RequestBody user updateTeacher){
        List errors = new ArrayList();
        HashMap data = new HashMap();

            try {
                user originalTeacher=UserService.findById(id);
                user UpdatedUser = UserService.compareAndModifyUser(originalTeacher,updateTeacher);
                boolean updated=UserService.update(UpdatedUser);
                if(updated){
                    data.put("teacher",UserService.findByEmail(UpdatedUser.getEmail()));
                    return ResponseFactory.success(data,"Updated Teacher",null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.add(new ErrorMessage("Error", e.getMessage()));
            }
        return ResponseFactory.error(data,"Error Occured",errors);
    }





}
