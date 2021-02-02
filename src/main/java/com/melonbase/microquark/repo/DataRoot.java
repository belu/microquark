package com.melonbase.microquark.repo;

import java.util.Objects;

public class DataRoot {

  private String message;

  public DataRoot() {
    super();
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataRoot dataRoot = (DataRoot) o;
    return Objects.equals(message, dataRoot.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }
}
