package com.mortgage_calculator.controller;

import com.mortgage_calculator.dto.MortgageDto;
import com.mortgage_calculator.exceptions.ResponseHandler;
import com.mortgage_calculator.services.impl.MortgageServiceImpl;
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

    private final MortgageServiceImpl mortgageService;

    public MortgageController(MortgageServiceImpl mortgageService) {
        this.mortgageService = mortgageService;
    }

    @PostMapping
    public ResponseEntity<Object> calculateMortgage(@Valid @RequestBody MortgageDto payload) {
        Double mortgage = mortgageService.calculateMortgage(payload.getLoanAmount(), payload.getAnnualInterestRate(), payload.getLoanTermYears(), null);
        return ResponseHandler.generateSuccessResponse(HttpStatus.OK, mortgage);
    }
}
