package com.mortgage_calculator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InterestType {

    FIXED("Fixed"),
    VARIABLE("Variable");

    private final String value;
}
