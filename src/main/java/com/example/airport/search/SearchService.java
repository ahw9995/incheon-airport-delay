package com.example.airport.search;

import com.example.airport.common.entity.AirportInfo;
import java.util.List;

public interface SearchService {
  List<AirportInfo> getAirportList();
  List<String> getAirportCompanyList(String airportCode);
  CancellationInfo getCancellationInfo(String airportCode, String companyName);
}
