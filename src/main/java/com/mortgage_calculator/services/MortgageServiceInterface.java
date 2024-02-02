package com.mortgage_calculator.services;

public interface MortgageServiceInterface {

    Object calculateMortgage(Double loanAmount, Double annualInterestRate, Integer loanTermYears, String interestType);
}
