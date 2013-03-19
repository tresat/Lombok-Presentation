package com.tomtresansky.lombokpresentation.example06.synchronization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * Same test code as before, but running the lombok-ed class.
 */
public class DemoLombokSynchronization {
  @Test
  public void showItWorkingWithLombok() throws InterruptedException {
    final SynchronizedMethodWithLombok sm = new SynchronizedMethodWithLombok();

    // Create collection of one thousand threads
    final Collection<Callable<Integer>> threads = new ArrayList<Callable<Integer>>(100);
    for (int i = 0; i < 1000; i++) {
      threads.add(new Callable<Integer>() {
        @Override
        public Integer call() {
          // Each of which will increment one thousand times
          for (int i = 0; i < 1000; i++) {
            sm.increment();
          }

          return sm.getCount();
        }
      });
    }

    /*------------------------------------------------------------------------------------ *
     * ---------------------------- SAME CHANGE AS HANGING TEST -------------------------- *
     * ----------------------------------------------------------------------------------- *
     */
    new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized(sm) {
          while (true) {
            ; // simulate some long running process...
          }
        }
      }
    }).start();

    // Submit them
    final ExecutorService es = Executors.newFixedThreadPool(100);
    final List<Future<Integer>> results = es.invokeAll(threads);

    // Busy wait until all are done
    while (true) {
      boolean allDone = true;

      for (final Future<Integer> result : results) {
        if (!result.isDone()) {
          allDone = false;
          break;
        }
      }

      if (allDone) {
        break;
      }
    }

    // Result should be 1000 * 1000 = 1000000
    assertEquals(1000000, sm.getCount());
  }
}
