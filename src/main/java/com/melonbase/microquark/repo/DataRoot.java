package com.melonbase.microquark.repo;

public class DataRoot {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "DataRoot{" +
        "message='" + message + '\'' +
        '}';
  }
}
