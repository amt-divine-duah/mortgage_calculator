package com.mortgage_calculator.controller;

import com.mortgage_calculator.dto.MortgageDto;
import com.mortgage_calculator.exceptions.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mortgage")
public class MortgageController {

    @PostMapping
    public ResponseEntity<Object> calculateMortgage(@Valid @RequestBody MortgageDto payload) {
        return ResponseHandler.generateSuccessResponse(HttpStatus.OK, payload);
    }
}
