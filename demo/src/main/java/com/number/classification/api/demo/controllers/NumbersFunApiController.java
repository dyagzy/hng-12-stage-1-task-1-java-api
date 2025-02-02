package com.number.classification.api.demo.controllers;

import com.number.classification.api.demo.service.NumbersFunUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/numbers")
@CrossOrigin(origins = "*")
public class NumbersFunApiController {
  private final NumbersFunUseCase numbersFunUseCase;

  public NumbersFunApiController(NumbersFunUseCase numbersFunUseCase1) {
    this.numbersFunUseCase = numbersFunUseCase1;
  }

  @GetMapping("/{number}")
  public ResponseEntity<Object> getNumbersFun(@PathVariable int number) {
    if (isValidNumber(number)) {
      return ResponseEntity.ok(numbersFunUseCase.getFunFactNumber(number));
    }
    else{
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestResponse(number, true));
    }

  }

  private boolean isValidNumber(double number) {
    return number == Math.floor(number) && number >= 0;
  }

  @AllArgsConstructor
  private static class BadRequestResponse {
    private double number;
    private boolean isBadRequest;
  }
}
