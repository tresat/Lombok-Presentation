package com.tomtresansky.lombokpresentation.example01;

import lombok.Getter;

public class Getters {
  @Getter
  private String name;

  public Getters(final String name) {
    this.name = name;
  }
}
