package com.tomtresansky.lombokpresentation.agent03.redefiner;

public class Printer1 {
  private String name;
  private int count;

  public Printer1(final String name) {
    this.name = name;
  }

  public void print() {
    System.out.println("Printer1 instance printing name: " + name
        + ", called: " + ++count + " times.");
  }
}
