package com.tomtresansky.lombokpresentation.example04.equalsandhashcode;

import lombok.EqualsAndHashCode;

/**
 * Same fields, same equals and hashCode implementation as other code.
 */
@EqualsAndHashCode
public abstract class ShapeWithLombok {
  public String color;
  private int area = getArea();

  public abstract int getArea();
}
