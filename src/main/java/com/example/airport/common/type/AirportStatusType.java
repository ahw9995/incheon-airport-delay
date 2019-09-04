package com.example.airport.common.type;

public enum AirportStatusType {

  START("출발"),
  DELAY("지연"),
  CANCEL("취소"),
  RETURN("회항");

  private String text;

  AirportStatusType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
