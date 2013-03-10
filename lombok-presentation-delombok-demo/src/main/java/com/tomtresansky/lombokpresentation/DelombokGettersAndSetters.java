package com.tomtresansky.lombokpresentation;

import lombok.Data;

/**
 * Demonstrate delomboking.
 */
@Data
public class DelombokGettersAndSetters {
  private final int count;

  public static void main(final String... args) {
    final DelombokGettersAndSetters t = new DelombokGettersAndSetters(7);

    System.out.println("The count is: " + t.getCount());
  }
}
