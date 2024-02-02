package com.mortgage_calculator.services.impl;

import com.mortgage_calculator.entities.MortgageEntity;
import com.mortgage_calculator.enums.InterestType;
import com.mortgage_calculator.repositories.MortgageRepository;
import com.mortgage_calculator.services.MortgageServiceInterface;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MortgageServiceImpl implements MortgageServiceInterface {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int PERCENTAGE = 100;
    private final MortgageRepository mortgageRepository;

    public MortgageServiceImpl(MortgageRepository mortgageRepository) {
        this.mortgageRepository = mortgageRepository;
    }

    @Override
    public Double calculateMortgage(Double loanAmount, Double annualInterestRate, Integer loanTermYears, String interestType) {
        if (StringUtils.isBlank(interestType)) {
            interestType = InterestType.FIXED.getValue();
        }

        if (interestType.equalsIgnoreCase(InterestType.FIXED.getValue())) {
            Double calculatedFixedMortgage = calculateFixedMortgage(loanAmount, annualInterestRate, loanTermYears);
            MortgageEntity mortgageEntity = MortgageEntity.builder().annualInterestRate(annualInterestRate).loanAmount(loanAmount).loanTermYears(loanTermYears).interestType(InterestType.FIXED).build();
            mortgageRepository.save(mortgageEntity);
            return calculatedFixedMortgage;
        } else {
            System.out.println("Calculating for variable rate mortgage.");
//            return loanAmount * (annualInterestRate * Math.pow(1 + annualInterestRate, loanTermYears)) / (Math.pow(1 + annualInterestRate, loanTermYears) - 1);
            return 0.0;
        }
    }

    private Double calculateFixedMortgage(Double loanAmount, Double annualInterestRate, Integer loanTermYears) {
        int numOfPayments = loanTermYears * MONTHS_IN_YEAR;
        double monthlyInterestRate = (annualInterestRate / PERCENTAGE) / MONTHS_IN_YEAR;

        return loanAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numOfPayments)) / (Math.pow(1 + monthlyInterestRate, numOfPayments) - 1);
    }
}
