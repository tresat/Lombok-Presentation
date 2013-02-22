package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import lombok.Getter;

/**
 * Demonstrates Lombok's @Getter using simplest possible use case.
 * 
 * This class has no methods...yet a getFirstName() method appears in the Outline
 * view, inserted via Lombok.
 */
public class Getters {
  /*
   * No need to write a method, just annotate w/ lombok's {@link Getter}.
   */
  @Getter
  public String firstName;

  /*
   * Constructor stores first name, so we have something to return.
   */
  public Getters(final String firstName) {
    this.firstName = firstName;
  }
}
