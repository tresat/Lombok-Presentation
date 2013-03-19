package com.tomtresansky.lombokpresentation.agent02.dynamicload;

public class Printer1 {
  private String name;

  public Printer1(final String name) {
    this.name = name;
  }

  public void print() {
    System.out.println("Printer1 instance printing name: " + name);
  }
}
