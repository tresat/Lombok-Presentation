package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import lombok.Getter;

/**
 * This class has no methods...yet a getName() method appears in the Outline
 * view, inserted via Lombok.
 */
public class Getters {
  /**
   * No need to write a method, just annotate w/ lombok's {@link Getter}.
   */
  @Getter
  private String name;

  public Getters(final String name) {
    this.name = name;
  }
}
