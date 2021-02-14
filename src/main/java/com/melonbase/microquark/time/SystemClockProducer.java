package com.melonbase.microquark.time;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.time.Clock;

@Singleton
public class SystemClockProducer {

  @Produces
  public Clock getClock() {
    return Clock.systemDefaultZone();
  }
}
