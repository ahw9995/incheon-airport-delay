package com.example.airport.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancellationInfo {
  // 항공사 이름
  @Default
  private String airportCompanyName = "";
  // 출발 공항 이름
  @Default
  private String departureAirportName = "인천";
  // 도착지 이름
  @Default
  private String destinationAirportName = "";
  // 도착지 코드
  @Default
  private String destinationAirportCode = "";
  // 10분 이내 출발 확률
  @Default
  private String departureWithinTenMinutes = "";
  // 초과 지연 확률
  @Default
  private String DelayProbability = "";
  // 총 운항 횟수
  @Default
  private String totalNumberOfFlights = "";
  // 총 지연 횟수
  @Default
  private String totalDelays = "";
  // 총 결항 횟수
  @Default
  private String totalCancellations = "";
  // 총 회항 횟수
  @Default
  private String totalNumberOfReturns = "";
  // 평균 지연 시간
  @Default
  private String averageLatency = "";
}
