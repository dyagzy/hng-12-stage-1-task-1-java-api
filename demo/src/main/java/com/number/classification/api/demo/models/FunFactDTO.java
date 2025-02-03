package com.number.classification.api.demo.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunFactDTO {
  private int number;
  private boolean is_prime;
  private boolean is_perfect;
  private List<String> properties;
  private int digit_sum;
  private String fun_fact;


}
