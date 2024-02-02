package com.mortgage_calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mortgage_calculator.enums.InterestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MortgageDto {

    @JsonProperty("loan_amount")
    @NotNull(message = "Loan amount cannot be blank")
    @Range(min = 1000, max = 1_000_000, message = "Loan amount must be between 1000 and 1,000,000")
    private Double loanAmount;

    @JsonProperty("interest_rate")
    @NotNull(message = "Interest rate cannot be blank")
    @Range(min = 1, max = 30, message = "Interest rate must be between 1 and 30")
    private Double annualInterestRate;

    @JsonProperty("loan_term_years")
    @NotNull(message = "Loan term years cannot be blank")
    @Range(min = 1, max = 30, message = "Loan term years must be between 1 and 30")
    private Integer loanTermYears;

    @JsonProperty("interest_type")
    private InterestType interestType;
}
