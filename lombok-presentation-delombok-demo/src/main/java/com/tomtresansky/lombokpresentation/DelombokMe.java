package com.tomtresansky.lombokpresentation;

import lombok.Data;

/**
 * Demonstrate delomboking.
 */
@Data
public class DelombokMe {
  private final int count;
  private final String name;

  public static void main(final String... args) {
    final DelombokMe t = new DelombokMe(7, "Seven");

    System.out.println("The count is: " + t.getCount());
  }
}
