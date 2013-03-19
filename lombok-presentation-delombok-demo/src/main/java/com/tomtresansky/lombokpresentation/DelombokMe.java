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
    // RequiredArgs constructor provided by @Data
    final DelombokMe t = new DelombokMe(7, "Seven");
    // Getters for all fields provided by @Data
    System.out.println("The count is: " + t.getCount());
  }
}
