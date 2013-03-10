package com.tomtresansky.lombokpresentation;

import lombok.Data;

/**
 * Demonstrate delomboking.
 */
@Data
public class DelombokMe {
  private final int count;

  public static void main(final String... args) {
    final DelombokMe t = new DelombokMe(7);

    System.out.println("The count is: " + t.getCount());
  }
}
