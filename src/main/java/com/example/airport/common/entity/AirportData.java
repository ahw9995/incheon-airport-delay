package com.example.airport.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "airport_data")
public class AirportData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "company", length = 128)
  private String company;

  @Column(name = "airplane_code", length = 64)
  private String airplaneCode;

  @Column(name = "airport_code", length = 10)
  private String airportCode;

  @Column(name = "airport_name", length = 256)
  private String airportName;

  @Column(name = "estimated_time", length = 12)
  private String estimatedTime;

  @Column(name = "departure_time", length = 12)
  private String departureTime;

  @Column(name = "delay_time_min")
  private Long delayTimeMin;

  @Column(name = "status", length = 12)
  private String status;
}
