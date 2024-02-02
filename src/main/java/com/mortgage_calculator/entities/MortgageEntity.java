package com.mortgage_calculator.entities;

import com.mortgage_calculator.enums.InterestType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "mortgage")
public class MortgageEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "loan_amount", nullable = false)
    private Double loanAmount;

    @Column(name = "interest_rate", nullable = false)
    private Double annualInterestRate;

    @Column(name = "loan_term_years", nullable = false)
    private Integer loanTermYears;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_type", nullable = false, columnDefinition = "varchar(10) default 'FIXED'")
    private InterestType interestType;

}