package org.moeez.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseFactory {
    public static ResponseEntity success(Map data, String message, List errors) {
        HashMap response = new HashMap();
        response.put("success", true);
        response.put("data", data);
        response.put("errors", errors);
        response.put("message", message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity error(Map data, String message, List errors) {
        HashMap response = new HashMap();
        response.put("success", false);
        response.put("data", data);
        response.put("errors", errors);
        response.put("message", message);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
