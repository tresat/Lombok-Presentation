package com.tomtresansky.lombokpresentation.example06.synchronization;

import lombok.Getter;
import lombok.Synchronized;

/**
 * Example class with a synchronized method.
 */
class SynchronizedMethodWithLombok {
  @Getter
  private int count;

  @Synchronized
  public void increment() {
    final int oldcount = count;
    count = oldcount + 1;
  }
}

/**
 * Should be equivalent (in terms of locking) as {@link SynchronizedMethodWithLombok}.
 */
class SynchronizedMethodWithoutLombokEquivalent {
  @Getter
  private int count;

  /*
   * Lombok creates 0-sized object array to use as lock to avoid serialization
   * issues w/ plain Object, which is NOT serializable
   */
  private final Object $lock = new Object[0];

  public void increment() {
    synchronized ($lock) { // synchronize on private, unexposed and unexposable, forever hidden lock
      final int oldcount = count;
      count = oldcount + 1;
    }
  }
}