package com.tomtresansky.lombokpresentation.example03;

import lombok.ToString;

/**
 * Demonstrates Lombok's @ToString method.
 * 
 * Getters/Setters omitted throughout, to simplify code.
 */
public class ToStrings {
  /*
   * Basic usage.
   */
  @ToString
  private static class Car1 {
    private static int wheels = 4;

    private String make = "Honda";
    private String model = "Civic";
    private int year = 2009;
    private String color = "grey";
    private int doors = 4;

    private String[] passengers = { "Jerry", "George", "Elaine", "Kramer" };

    public static void main(final String... args) {
      final Car1 car = new Car1();

      System.out.println("Car 1: " + car);
    }
  }

  /*
   * Omit field names (defaults to including field names).
   */
  @ToString(includeFieldNames=false)
  private static class Car2 {
    private static int wheels = 4;

    private String make = "Honda";
    private String model = "Civic";
    private int year = 2009;
    private String color = "grey";
    private int doors = 4;

    private String[] passengers = { "Jerry", "George", "Elaine", "Kramer" };

    public static void main(final String... args) {
      final Car2 car = new Car2();

      System.out.println("Car 2: " + car);
    }
  }

  /*
   * Omit certain fields.
   */
  @ToString(exclude={"doors", "passengers"})
  private static class Car3 {
    private static int wheels = 4;

    private String make = "Honda";
    private String model = "Civic";
    private int year = 2009;
    private String color = "grey";
    private int doors = 4;

    private String[] passengers = { "Jerry", "George", "Elaine", "Kramer" };

    public static void main(final String... args) {
      final Car3 car = new Car3();

      System.out.println("Car 3: " + car);
    }
  }

  /*
   * Alternately, specify to only include certain fields.
   */
  @ToString(of={"make", "model", "color"})
  private static class Car4 {
    private static int wheels = 4;

    private String make = "Honda";
    private String model = "Civic";
    private int year = 2009;
    private String color = "grey";
    private int doors = 4;

    private String[] passengers = { "Jerry", "George", "Elaine", "Kramer" };

    public static void main(final String... args) {
      final Car4 car = new Car4();

      System.out.println("Car : " + car);
    }
  }

  /*
   * If a toString method already exists, do nothing and throw a compiler warning.
   */
  @ToString
  private static class Car5 {
    private static int wheels = 4;

    private String make = "Honda";
    private String model = "Civic";
    private int year = 2009;
    private String color = "grey";
    private int doors = 4;

    private String[] passengers = { "Jerry", "George", "Elaine", "Kramer" };

    @Override
    public String toString() {
      return "I cause a warning on the @toString param!";
    }
  }

  /*
   * Can chain super class calls!
   * 
   * Here is a base class...
   */
  @ToString
  private static class Vehicle {
    private boolean canFly = false;
    private boolean canFloat = false;
    private String color = "grey";

    private String[] passengers = { "Jerry", "George", "Elaine", "Kramer" };
  }

  /*
   * Extended here.  The toString should list fields on the super class as well.
   */
  @ToString(callSuper=true)
  private static class Car6 extends Vehicle {
    private static int wheels = 4;

    private String make = "Honda";
    private String model = "Civic";
    private int year = 2009;
    private int doors = 4;

    public static void main(final String... args) {
      final Car6 car = new Car6();

      System.out.println("Car : " + car);
    }
  }
}
