package com.example.airport.refining;

import com.example.airport.common.entity.AirportData;
import com.example.airport.common.repository.AirportDataRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DataRefiningServiceImpl implements DataRefiningService {

  private AirportDataRepository airportDataRepository;

  @Override
  public void dataRefining() {
    List<AirportData> airportDataList = this.airportDataRepository.findAll();

    for (AirportData data : airportDataList) {
      String estimatedTime = data.getEstimatedTime();
      String departureTime = data.getDepartureTime();

      if (estimatedTime.length() == 1 || departureTime.length() == 1) {
        continue;
      }

      String[] estimatedArray = estimatedTime.split(":");
      String[] departureArray = departureTime.split(":");
      LocalDateTime estimated = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
      LocalDateTime departure = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

      estimated = estimated
          .plusHours(Long.parseLong(estimatedArray[0]))
          .plusMinutes(Long.parseLong(estimatedArray[1]));

      departure = departure
          .plusHours(Long.parseLong(departureArray[0]))
          .plusMinutes(Long.parseLong(departureArray[1]));

      /*
        출발시간이 예정시간보다 작을 경우 23시에서 00시로 넘어갔을 수 도 있음
        그래서 이와 같은 상황의 경우 + 1day를 해줘야 함
       */
      if (Long.parseLong(estimatedTime.replaceAll(":", "")) >
          Long.parseLong(departureTime.replaceAll(":", ""))) {
        departure = departure.plusDays(1);
      }
      data.setDelayTimeMin(Duration.between(estimated, departure).toMinutes());
    }
  }
}
