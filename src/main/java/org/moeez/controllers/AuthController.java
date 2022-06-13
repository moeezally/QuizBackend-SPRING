package org.moeez.controllers;

import org.moeez.configuration.ErrorMessage;
import org.moeez.models.loginObject;
import org.moeez.models.user;
import org.moeez.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    userService userService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody loginObject Login) {
        List errors = new ArrayList();
        String authToken = null;
            authToken = userService.authenticate(Login.email, Login.password);
            if (authToken != null) {
                System.out.println(authToken);
                Map response = new HashMap();
                response.put("success", true);
                response.put("message", "Logged In");
                response.put("token", authToken);
                return new ResponseEntity(response, HttpStatus.OK);
            } else {
                errors.add(new ErrorMessage("authentication", "Invalid username or password"));
            }

        return ResponseFactory.error(null, "Invalid username or password", errors);

    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody user User) {
        List errors = new ArrayList();
        Map response = new HashMap();

        try {
            userService.createTeacher(User);
            response.put("success", true);
            response.put("message", "SignUp Succesful");
            return new ResponseEntity(response, HttpStatus.OK);
        }
         catch (Exception e) {
            e.printStackTrace();
            errors.add(new ErrorMessage("Error",e.getMessage()));
        }



        return ResponseFactory.error(null, "SignUp Not Successful", errors);

    }


}
