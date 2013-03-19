package com.tomtresansky.lombokpresentation.example04.equalsandhashcode;

/**
 * Basic abstract Shape class.
 * 
 * Has an area and a color.
 */
public abstract class ShapeWithoutLombok {
  public String color;
  private int area = getArea();

  public abstract int getArea();

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + area;
    result = prime * result + ((color == null) ? 0 : color.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof ShapeWithoutLombok)) {
      return false;
    }

    final ShapeWithoutLombok other = (ShapeWithoutLombok) obj;
    if (!other.canEqual(this)) {
      return false;
    }

    if (area != other.area) {
      return false;
    }
    if (color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!color.equals(other.color)) {
      return false;
    }

    return true;
  }

  public boolean canEqual(final Object other) {
    return other instanceof ShapeWithoutLombok;
  }
}
