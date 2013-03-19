package com.tomtresansky.lombokpresentation.example06.synchronization;

import lombok.Getter;

/**
 * Example class with a synchronized method.
 */
class BadSynchronizedMethod {
  @Getter
  private int count;

  public synchronized void increment() {
    final int oldcount = count;
    count = oldcount + 1;
  }
}

/**
 * Should be equivalent (in terms of locking) as {@link BadSynchronizedMethod}.
 */
class BadSynchronizedMethodEquivalent {
  @Getter
  private int count;

  public void increment() {
    synchronized(this) {
      final int oldcount = count;
      count = oldcount + 1;
    }
  }
}

