package com.mortgage_calculator.services;

public interface MortgageServiceInterface {

    Double calculateMortgage(Double loanAmount, Double annualInterestRate, Integer loanTermYears, String interestType);
}
