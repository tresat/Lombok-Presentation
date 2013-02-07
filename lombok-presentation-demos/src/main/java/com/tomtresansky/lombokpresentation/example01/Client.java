package com.tomtresansky.lombokpresentation.example01;

public class Client {
  public static void main(final String[] args) {
    final Getters g = new Getters("Stanley");

    System.out.println(g.getName());
  }
}
