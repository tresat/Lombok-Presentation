package com.tomtresansky.lombokpresentation.agent02.dynamicload;

public class Printer2 {
  private String name;

  public Printer2(final String name) {
    this.name = name;
  }

  public void print() {
    System.out.println("Printer2 instance printing name: " + name);
  }
}