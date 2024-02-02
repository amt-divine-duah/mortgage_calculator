package com.mortgage_calculator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseHandler {

    public static ResponseEntity<Object> generateSuccessResponse(HttpStatus status, Object data) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("status", status.value());
        map.put("data", data);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateErrorResponse(HttpStatus status, Object error) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("status", status.value());
        map.put("error", error);

        return new ResponseEntity<>(map, status);
    }
}
