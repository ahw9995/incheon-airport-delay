package com.example.airport.common.repository;

import com.example.airport.common.entity.AirportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDataRepository extends JpaRepository<AirportData, Long> {
  // 토탈 수
  Long countAllByAirportCodeAndCompany(String airportCode, String company);

  // 10분 이내 수
  Long countAllByAirportCodeAndCompanyAndDelayTimeMinLessThan(String airportCode, String company, Long delay);

  // 60분 이상 수
  Long countAllByAirportCodeAndCompanyAndDelayTimeMinGreaterThan(String airportCode, String company, Long delay);

  // 상태 조회
  Long countAllByAirportCodeAndCompanyAndStatus(String airportCode, String company, String status);

  @Query(value = "select sum(delay_time_min) from airport_data where airport_code = :airportCode and company = :company",
  nativeQuery = true)
  Long sumByAirportCodeAndComapany(@Param("airportCode") String airportCode, @Param("company") String company);
}
