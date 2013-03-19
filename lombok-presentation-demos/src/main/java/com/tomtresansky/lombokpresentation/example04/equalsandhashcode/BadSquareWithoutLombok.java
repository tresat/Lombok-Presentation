package com.tomtresansky.lombokpresentation.example04.equalsandhashcode;

import java.util.Arrays;

/**
 * This class is ALMOST equivalent to the other Square classes...
 * 
 * Except for a single character difference which RUINS EVERYTHING!!!
 */
public class BadSquareWithoutLombok extends ShapeWithoutLombok {
  private final int width, height;

  private transient int transientVar = 10;

  private String name;

  public String[] tags;
  public double score;

  public BadSquareWithoutLombok(final String name, final int width, final int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getArea() {
    return width * height;
  }

  public String getName() {
    return name;
  }

  // Everything past this point would be auto-generated by Lombok via @EqualsAndHashCode

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof BadSquareWithoutLombok)) {
      return false;
    }

    if (!super.equals(obj)) {
      return false;
    }

    final BadSquareWithoutLombok other = (BadSquareWithoutLombok) obj;
    if (!other.canEqual(this)) {
      return false;
    }

    if (this.getName() == null ? other.getName() != null : this.getName().equals(other.getName())) {
      return false;
    }
    if (Double.compare(this.score, other.score) != 0) {
      return false;
    }
    if (!Arrays.deepEquals(this.tags, other.tags)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int PRIME = 31;
    int result = 1;
    final long temp1 = Double.doubleToLongBits(this.score);
    result = (result * PRIME) + (this.name == null ? 0 : this.name.hashCode());
    result = (result * PRIME) + (int) (temp1 ^ (temp1 >>> 32));
    result = (result * PRIME) + Arrays.deepHashCode(this.tags);
    return result;
  }

  @Override
  public boolean canEqual(final Object other) {
    return other instanceof BadSquareWithoutLombok;
  }
}
