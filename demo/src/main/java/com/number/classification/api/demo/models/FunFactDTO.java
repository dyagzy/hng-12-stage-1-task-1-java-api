package com.number.classification.api.demo.models;

import java.util.List;

public record FunFactDTO(
    int number,
    boolean isPrime,
    boolean isPerfect,
    List<String> properties,
    int digit_sum,
    String fun_fact) {}
