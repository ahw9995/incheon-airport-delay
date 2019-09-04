package com.example.airport.refining;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DataRefiningController {

  private DataRefiningService dataRefiningService;

  @GetMapping("/refining-data")
  public void refiningData() {
//    this.dataRefiningService.dataRefining();
  }
}
