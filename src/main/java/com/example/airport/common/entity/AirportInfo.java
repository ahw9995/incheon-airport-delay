package com.example.airport.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Entity
@Table(name = "airport_info")
public class AirportInfo {

  @Id
  @Column(name = "airport_code", updatable = false, nullable = false)
  private String airportCode;

  @Column(name = "airport_name", length = 256)
  private String airportName;

  @Column(name = "is_show", length = 1)
  private String isShow;

  @PrePersist
  void prePersist() {
    this.isShow = StringUtils.isEmpty(this.isShow) ? "Y" : this.isShow;
  }
}
