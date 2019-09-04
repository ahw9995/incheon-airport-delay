package com.example.airport.search;

import com.example.airport.common.entity.AirportInfo;
import com.example.airport.common.repository.AirportDataRepository;
import com.example.airport.common.repository.AirportInfoRepository;
import com.example.airport.common.type.AirportStatusType;
import com.example.airport.common.type.TableStatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

  private AirportInfoRepository airportInfoRepository;
  private AirportDataRepository airportDataRepository;

  private static final Long DELAY_MINUTE = 30L;
  private static final Long MINIMUM_MINUTE = 10L;

  @Override
  public List<AirportInfo> getAirportList() {
    return this.airportInfoRepository
        .findAllByIsShowOrderByAirportNameAsc(TableStatusType.TRUE.getStr());
  }

  @Override
  public List<String> getAirportCompanyList(String airportCode) {
    return this.airportInfoRepository.findAllByAirportCodeAndCompanyIsNotNullGroupBy(airportCode);
  }

  @Override
  public CancellationInfo getCancellationInfo(String airportCode, String companyName) {

    AirportInfo info = this.airportInfoRepository.findByAirportCode(airportCode);

    Long totalCount = this.getTotalCount(airportCode, companyName);

    Long departureWithinTenMinutesCount =
        this.getDepartureWithinTenMinutesCount(airportCode, companyName);

    Long oneHourDelayProbabilityCount =
        this.getOneHourDelayProbabilityCount(airportCode, companyName);

    Long returnCount = this.getReturnCount(airportCode, companyName);

    Long cancellationCount = this.getCancellationCount(airportCode, companyName);

    Long totalDelaySum = this.getOneHourDelayProbabilityMinuteSum(airportCode, companyName);

    return CancellationInfo
        .builder()
        .airportCompanyName(companyName)
        .destinationAirportName(info.getAirportName())
        .destinationAirportCode(airportCode)
        .departureWithinTenMinutes(this.calculatePercentageToString(totalCount, departureWithinTenMinutesCount, TableStatusType.TRUE.getStr()))
        .DelayProbability(this.calculatePercentageToString(totalCount, oneHourDelayProbabilityCount, TableStatusType.TRUE.getStr()))
        .totalNumberOfFlights(String.valueOf(totalCount))
        .totalDelays(String.valueOf(oneHourDelayProbabilityCount))
        .totalCancellations(String.valueOf(cancellationCount))
        .totalNumberOfReturns(String.valueOf(returnCount))
        .averageLatency(this.calculatePercentageToString(totalCount, totalDelaySum, TableStatusType.FALSE.getStr()))
        .build();
  }

  /**
   * 총 운항 횟수 조회
   * @param airportCode 공항코드
   * @param company 항공사이름
   * @return 횟수
   */
  private Long getTotalCount(String airportCode, String company) {
    return
        this.airportDataRepository.countAllByAirportCodeAndCompany(airportCode, company);
  }

  /**
   * 10분 이내 출발 횟수
   * @param airportCode 공항코드
   * @param company 항공사이름
   * @return 횟수
   */
  private Long getDepartureWithinTenMinutesCount(String airportCode, String company) {
    return
        this.airportDataRepository
            .countAllByAirportCodeAndCompanyAndDelayTimeMinLessThan(airportCode, company, MINIMUM_MINUTE);
  }

  /**
   * 1시간 이상 지연 횟수
   * @param airportCode 공항코드
   * @param company 항공사이름
   * @return 1시간 이상 지연 횟수
   */
  private Long getOneHourDelayProbabilityCount(String airportCode, String company) {
    return
        this.airportDataRepository
            .countAllByAirportCodeAndCompanyAndDelayTimeMinGreaterThan(airportCode, company, DELAY_MINUTE);
  }

  /**
   * 회항 횟수
   * @param airportCode 공항코드
   * @param company 항공사이름
   * @return 회항 횟수
   */
  private Long getReturnCount(String airportCode, String company) {
    return this.getStatusCount(airportCode, company, AirportStatusType.RETURN.getText());
  }

  /**
   * 취소 횟수
   * @param airportCode 공항코드
   * @param company 항공사이름
   * @return 취소 횟수
   */
  private Long getCancellationCount(String airportCode, String company) {
    return this.getStatusCount(airportCode, company, AirportStatusType.CANCEL.getText());
  }

  private Long getStatusCount(String airportCode, String company, String status) {
    return
        this.airportDataRepository
            .countAllByAirportCodeAndCompanyAndStatus(airportCode, company, status);
  }

  /**
   * 전체 대비 퍼센트 계산
   * @param totalCount 전체 건수
   * @param targetCount 대상 건수
   * @return 결과 퍼센트
   */
  private double calculatePercentage(Long totalCount, Long targetCount) {

    if (totalCount == 0 || targetCount == 0) {
      return 0L;
    }

    return (double) targetCount / (double) totalCount;
  }

  /**
   *
   * @param totalCount
   * @param targetCount
   * @param type
   * @return
   */
  private String calculatePercentageToString(Long totalCount, Long targetCount, String type) {

    double percentage = this.calculatePercentage(totalCount, targetCount);

    if (TableStatusType.TRUE.getStr().equals(type)) {
      return String.format("%.2f", percentage * 100);
    }

    return String.format("%.2f", percentage);
  }

  /**
   * 지연된 전체 시간(단위: 분)
   * @param airportCode 공항코드
   * @param company 항공사이름
   * @return 지연된 전체 시간
   */
  private Long getOneHourDelayProbabilityMinuteSum(String airportCode, String company) {
    return
        this.airportDataRepository.sumByAirportCodeAndComapany(airportCode, company);
  }
}
