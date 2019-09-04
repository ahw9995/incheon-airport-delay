package com.example.airport.common.repository;

import com.example.airport.common.entity.AirportInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportInfoRepository extends JpaRepository<AirportInfo, String> {

  AirportInfo findByAirportCode(String airportCode);

  List<AirportInfo> findAllByIsShowOrderByAirportNameAsc(String isShow);

  @Query(value = "select company from airport_data where airport_code = :airportCode and company != ''\n"
      + "  group by company\n"
      + "  order by company asc", nativeQuery = true)
  List<String> findAllByAirportCodeAndCompanyIsNotNullGroupBy(@Param("airportCode") String airportCode);

}
