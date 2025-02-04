package com.number.classification.api.demo.controllers;

import com.number.classification.api.demo.service.NumbersFunUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/numbers")
@CrossOrigin(origins = "*")
public class NumbersFunApiController {
  private final NumbersFunUseCase numbersFunUseCase;

  public NumbersFunApiController(NumbersFunUseCase numbersFunUseCase1) {
    this.numbersFunUseCase = numbersFunUseCase1;
  }

  @GetMapping
  public ResponseEntity<Object> getNumbersFun(
      @RequestParam(value = "number", required = false) String number) {
    if (number == null || number.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new BadRequestResponse(number, "true"));
    }
    if (isValidNumber(number)) {
      return ResponseEntity.ok(numbersFunUseCase.getFunFactNumber(Integer.parseInt(number)));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new BadRequestResponse(number, "true"));
    }
  }

  private boolean isValidNumber(String number) {

    return number.matches("\\d+");
  }

  @AllArgsConstructor
  @Data
  private static class BadRequestResponse {
    private String number;
    private String error;
  }
}

//
// (@RequestParam(value = "number", required = false) String numberParam) {
//    if (numberParam == null || numberParam.isEmpty()) {
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("number", "Missing
// 'number' parameter"));
