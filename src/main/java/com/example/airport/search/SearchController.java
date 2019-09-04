package com.example.airport.search;

import com.example.airport.common.dto.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchController {

  private SearchService searchService;

  @GetMapping("/v1/airport/list")
  public ResponseEntity<ResponseObject> getAirportList() {
    ResponseObject result = ResponseObject.builder()
        .code(HttpStatus.OK.toString())
        .data(this.searchService.getAirportList())
        .build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/v1/airport/companies/{airportCode}")
  public ResponseEntity<ResponseObject> getAirportCompanyList(
      @PathVariable(value = "airportCode") String airportCode) {
    ResponseObject result = ResponseObject.builder()
        .code(HttpStatus.OK.toString())
        .data(this.searchService.getAirportCompanyList(airportCode))
        .build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/v1/airport/cancellation-rate/{companyName}/{destinationCode}")
  public ResponseEntity<ResponseObject> getCancellationRate(
      @PathVariable(value = "companyName") String companyName,
      @PathVariable(value = "destinationCode") String destinationCode) {
    ResponseObject result = ResponseObject.builder()
        .code(HttpStatus.OK.toString())
        .data(this.searchService.getCancellationInfo(destinationCode, companyName))
        .build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
