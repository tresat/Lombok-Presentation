package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import lombok.Getter;

/**
 * Demonstrates Lombok's {@link Getter} using simplest possible use case.
 * 
 * This class has no methods...yet a getFirstName() method appears in the Outline
 * view, inserted via Lombok.
 */
public final class Getters {
  // No need to write a getFirstName() method, just annotate w/ lombok's {@link Getter}.
  @Getter
  public String firstName;

  /**
   * Constructor stores first name, so we have something to return.
   */
  public Getters(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * Can call generated code DIRECTLY from the same class which is modified.
   */
  public void printName() {
    System.out.println(this.getFirstName() );

    // Eclipse flags a method which doesn't exist, like if we uncomment below
    // System.out.println(this.callNonExistantMethod());
  }
}
