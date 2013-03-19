package com.tomtresansky.lombokpresentation.agent03.redefiner;

public class Printer2 {
  private String name;
  private int count;

  public Printer2(final String name) {
    this.name = name;
  }

  public void print() {
    System.out.println("Printer2 instance printing name: " + name
        + ", called: " + ++count + " times.");
  }
}