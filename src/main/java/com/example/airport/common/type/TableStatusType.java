package com.example.airport.common.type;

public enum TableStatusType {

  TRUE("1", "Y", true),
  FALSE("0", "N", false);

  private String number;
  private String str;
  private boolean bool;

  TableStatusType(String number, String str, boolean bool) {
    this.number = number;
    this.str = str;
    this.bool = bool;
  }

  public String getNumber() {
    return number;
  }

  public String getStr() {
    return str;
  }

  public boolean isBool() {
    return bool;
  }
}
