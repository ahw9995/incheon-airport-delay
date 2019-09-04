package com.example.airport.view;

import com.example.airport.search.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class ViewController {

  private SearchService searchService;

  @GetMapping("/v1/main")
  public ModelAndView view() {
    ModelAndView mov = new ModelAndView();
    mov.setViewName("view/index");
    mov.addObject("airportList", this.searchService.getAirportList());
    return mov;
  }

}
