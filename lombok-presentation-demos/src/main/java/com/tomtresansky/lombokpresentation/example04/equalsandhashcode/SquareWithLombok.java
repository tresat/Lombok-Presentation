package com.tomtresansky.lombokpresentation.example04.equalsandhashcode;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/*
 * With lombok, focus on the important parts, ensure that equals and hashCode
 * ALWAYS stay in sync.
 */
@EqualsAndHashCode(callSuper=true)
public class SquareWithLombok extends ShapeWithLombok {
  private final int width, height;

  private transient int transientVar = 10;

  @Getter
  private String name;

  public String[] tags;
  public double score;

  public SquareWithLombok(final String name, final int width, final int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getArea() {
    return width * height;
  }
}
