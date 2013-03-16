package com.tomtresansky.lombokpresentation.example06.synchronization;

import lombok.Getter;

/**
 * Example class with a synchronized method.
 */
class SynchronizedMethod {
  @Getter
  private int count;

  public synchronized void increment() {
    count++;
  }
}

/**
 * Should be equivalent (in terms of locking) as {@link SynchronizedMethod}.
 */
class SynchronizedMethodEquivalent {
  @Getter
  private int count;

  public void increment() {
    synchronized(this) {
      count++;
    }
  }
}

