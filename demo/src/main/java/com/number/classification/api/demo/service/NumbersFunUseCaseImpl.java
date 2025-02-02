package com.number.classification.api.demo.service;

import com.number.classification.api.demo.models.FunFactDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumbersFunUseCaseImpl implements NumbersFunUseCase {
  private final RestTemplate restClient;

  public NumbersFunUseCaseImpl(RestTemplate restClient) {

    this.restClient = restClient;
  }

  private boolean isArmstrongNumber(int number) {
    int originalNumber = number;
    int sum = 0;
    int digits = String.valueOf(number).length();

    while (number > 0) {
      int digit = number % 10;
      sum += Math.pow(digit, digits);
      number /= 10;
    }
    return sum == originalNumber;
  }

  private int sumDigits(int number) {
    int sum = 0;
    while (number != 0) {
      sum += number % 10;
      number /= 10;
    }
    return sum;
  }

  private boolean isValidNumber(double number) {
    return number == Math.floor(number) && number >= 0;
  }

  private boolean isPerfectNumber(int number) {
    if (!(number > 5)) {
      return false; // Perfect numbers start from 6 upwards
    }

    int sum = 1;
    for (int i = 2; i <= number / 2; i++) {
      if (number % i == 0) {
        sum += i;
      }
    }

    return sum == number;
  }

  @Override
  public FunFactDTO getFunFactNumber(int number) {
    var funFactDTO = new FunFactDTO();

    String numbersUrl = String.format("http://numbersapi.com/%d/math", number);
    var funNumber = restClient.getForObject(numbersUrl, String.class);
    funFactDTO.setNumber(number);
    funFactDTO.set_prime(isPrimeNumber(number));
    funFactDTO.set_perfect(isPerfectNumber(number));
    funFactDTO.setProperties(validateArmstrong(number));
    funFactDTO.setDigit_sum(sumDigits(number));
    funFactDTO.setFun_fact(funNumber);

    return funFactDTO;
  }

  boolean isPrimeNumber(int number) {
    return number % 2 == 0;
  }

  private List<String> validateArmstrong(int number) {
    List<String> out;
    boolean isArmstrong = isArmstrongNumber(number);
    boolean isEven = number % 2 == 0;

    if (isArmstrong && isEven) {
      out = List.of("armstrong", "even");
    } else if (isArmstrong) {
      out = List.of("armstrong", "odd");
    } else if (isEven) {
      out = List.of("even");
    } else {
      out = List.of("odd");
    }

    return out;
  }
}
