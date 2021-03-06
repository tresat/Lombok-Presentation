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

public final class DemoBadSynchronization {
  @Test
  public void showItWorking() throws InterruptedException {
    final SynchronizedMethod sm = new SynchronizedMethod();

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

    // Submit them
    final ExecutorService es = Executors.newFixedThreadPool(10);
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

  @Test
  public void showItFailing() throws InterruptedException {
    final SynchronizedMethod sm = new SynchronizedMethod();

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
     * ---------------------------- ONLY CHANGE ------------------------------------------ *
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
    final ExecutorService es = Executors.newFixedThreadPool(10);
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
