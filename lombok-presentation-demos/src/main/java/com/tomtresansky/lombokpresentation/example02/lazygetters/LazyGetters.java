package com.tomtresansky.lombokpresentation.example02.lazygetters;

import lombok.Getter;


/**
 * Demonstrates Lombok's @Getter using the lazy option.
 */
public class LazyGetters {
  @Getter(lazy=true)
  private final long hundrethFib = fibbonacci(100);

  @Getter(lazy=true) // Lazy works with expressions, too!
  private final String alphabet = "A" + "B" + "C" + "D" + "E" + "F" + "G" + "H" + "I" + "J" + "L" + "M" + "N" + "O" + "P" + "Q" + "R" + "S" + "T" + "U" + "V" + "W" + "X" + "Y" + "Z";

  /**
   * Computationally expensive Fibbonacci number calculation routine...we don't
   * want to run this until we need to!
   */
  private long fibbonacci(final int x) {
    System.out.println("***Computing Fibbonacci #: " + x + " ***");

    final long[] fibs = new long[Math.max(2, x + 1)];

    fibs[0] = 0;
    fibs[1] = 1;

    for (int i = 2; i <= x; i++) {
      fibs[i] = fibs[i-2] + fibs[i-1];
    }

    return fibs[x];
  }

  public static void main(final String... args) {
    System.out.println("Instantiate class...");
    final LazyGetters lg = new LazyGetters();

    System.out.println("\nAccess field directly (DON'T ACTUALLY DO THIS!)");
    System.out.println("hundrethFib: " + lg.hundrethFib);

    System.out.println("\nAccess field correctly, via generated Getter");
    System.out.println("getHundrethFib(): " + lg.getHundrethFib());

    System.out.println("\nSecond field access doesn't result in recalculation");
    System.out.println("getHundrethFib(): " + lg.getHundrethFib());

    System.out.println("\nAccess field initialized to expression directly (DON'T ACTUALLY DO THIS!)");
    System.out.println("alphabet: " + lg.alphabet);

    System.out.println("\nAccess field initialized to expression correctly, via generated Getter");
    System.out.println("getAlphabet(): " + lg.getAlphabet());
  }
}
