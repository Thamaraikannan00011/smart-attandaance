package com.example.smartattendance.util;

import java.time.LocalDate;

public final class DateUtils {
  private DateUtils() {}

  public static LocalDate today() {
    return LocalDate.now();
  }
}

