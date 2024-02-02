package com.mortgage_calculator.services.impl;

import com.mortgage_calculator.entities.MortgageEntity;
import com.mortgage_calculator.enums.InterestType;
import com.mortgage_calculator.repositories.MortgageRepository;
import com.mortgage_calculator.services.MortgageServiceInterface;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MortgageServiceImpl implements MortgageServiceInterface {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int PERCENTAGE = 100;
    private final MortgageRepository mortgageRepository;

    public MortgageServiceImpl(MortgageRepository mortgageRepository) {
        this.mortgageRepository = mortgageRepository;
    }

    @Override
    public Object calculateMortgage(Double loanAmount, Double annualInterestRate, Integer loanTermYears, String interestType) {
        if (StringUtils.isBlank(interestType)) {
            interestType = InterestType.FIXED.getValue();
        }

        if (interestType.equalsIgnoreCase(InterestType.FIXED.getValue())) {
            Object calculatedFixedMortgage = calculateFixedMortgage(loanAmount, annualInterestRate, loanTermYears);
            MortgageEntity mortgageEntity = MortgageEntity.builder().annualInterestRate(annualInterestRate).loanAmount(loanAmount).loanTermYears(loanTermYears).interestType(InterestType.FIXED).build();
            List<Object> paymentSchedule = paymentSchedule(loanAmount, loanTermYears, annualInterestRate);
            mortgageRepository.save(mortgageEntity);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("fixed_mortgage", calculatedFixedMortgage);
            map.put("payment_schedule", paymentSchedule);
            return map;
        } else {
            System.out.println("Calculating for variable rate mortgage.");
            return calculateVariableMortgage();
        }
    }

    private Object calculateFixedMortgage(Double loanAmount, Double annualInterestRate, Integer loanTermYears) {
        int numOfPayments = loanTermYears * MONTHS_IN_YEAR;
        double monthlyInterestRate = (annualInterestRate / PERCENTAGE) / MONTHS_IN_YEAR;

        return loanAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numOfPayments)) / (Math.pow(1 + monthlyInterestRate, numOfPayments) - 1);
    }

    private Double calculateVariableMortgage() {
        return 0.0;
    }

    private List<Object> paymentSchedule(Double loanAmount, Integer loanTermYears, Double annualInterestRate) {

        ArrayList<Object> paymentSchedule = new ArrayList<>();

        for (int month = 1; month <= loanTermYears * MONTHS_IN_YEAR; month++) {
            Double calculatedBalance = calculateBalance(loanAmount, loanTermYears, month, annualInterestRate);
            HashMap<Object, Object> monthlyPayment = new HashMap<>();

            monthlyPayment.put("month", month);
            monthlyPayment.put("balance", calculatedBalance);

            paymentSchedule.add(monthlyPayment);
        }
        return paymentSchedule;
    }

    private Double calculateBalance(Double loanAmount, Integer loanTermYears, Integer numberOfPayments, Double annualInterestRate) {

        int numOfPayments = loanTermYears * MONTHS_IN_YEAR;
        double monthlyInterestRate = (annualInterestRate / PERCENTAGE) / MONTHS_IN_YEAR;

        return loanAmount * (Math.pow(1 + monthlyInterestRate, numOfPayments) - Math.pow(1 + monthlyInterestRate, numberOfPayments)) / (Math.pow(1 + monthlyInterestRate, numOfPayments) - 1);
    }
}
