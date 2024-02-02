package com.mortgage_calculator.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException and generate error response.
     *
     * @param  ex   the MethodArgumentNotValidException to handle
     * @return     the ResponseEntity object containing the error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    /**
     * Handles HttpMessageNotReadableException and generates an error response.
     *
     * @param  ex	the HttpMessageNotReadableException to be handled
     * @return     the ResponseEntity containing the error response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, "Invalid request body");
    }

    /**
     * A method to handle all unhandled exceptions.
     *
     * @param  ex	the exception to be handled
     * @return    	response entity containing the error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledExceptions(Exception ex) {
        return ResponseHandler.generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }
}
