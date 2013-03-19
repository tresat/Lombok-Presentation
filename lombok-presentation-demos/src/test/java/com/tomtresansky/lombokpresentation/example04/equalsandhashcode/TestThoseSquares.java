package com.tomtresansky.lombokpresentation.example04.equalsandhashcode;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestThoseSquares {
  @Test
  public void testEqualSquaresAreEqualWithoutLombok() {
    final SquareWithoutLombok s1 = new SquareWithoutLombok("Start", 20, 20);
    s1.color = "red";
    s1.tags = new String[]{"flat", "clear"};
    s1.score = 3.71;

    final SquareWithoutLombok s2 = new SquareWithoutLombok("Start", 20, 20);
    s2.color = "red";
    s2.tags = new String[]{"flat", "clear"};
    s2.score = 3.71;

    assertEquals("Squares should be equal", s1, s2);
  }

  @Test
  public void testEqualSquaresAreEqualWithLombok() {
    final SquareWithLombok s1 = new SquareWithLombok("Start", 20, 20);
    s1.color = "red";
    s1.tags = new String[]{"flat", "clear"};
    s1.score = 3.71;

    final SquareWithLombok s2 = new SquareWithLombok("Start", 20, 20);
    s2.color = "red";
    s2.tags = new String[]{"flat", "clear"};
    s2.score = 3.71;

    assertEquals("Squares should be equal", s1, s2);
  }

  @Test
  public void testBadSquaresAreNotEqual() {
    final BadSquareWithoutLombok s1 = new BadSquareWithoutLombok("Start", 20, 20);
    s1.color = "red";
    s1.tags = new String[]{"flat", "clear"};
    s1.score = 3.71;

    final BadSquareWithoutLombok s2 = new BadSquareWithoutLombok("Start", 20, 20);
    s2.color = "red";
    s2.tags = new String[]{"flat", "clear"};
    s2.score = 3.71;

    // Running an equals check directly makes it obvious something is broke...
    assertEquals("Squares should be equal", s1, s2);

    // But say we did something more complex, like try and retrieve from a hash map...
    final Map<Object, Integer> map = new HashMap<Object, Integer>();

    /*
     * Common usage of map: store counts of each object in list with duplicates.
     * 
     * S1 should equal S2, should get single result with count = 2...instead get
     * 2 entries valued at 1.
     */
    final List<?> list = Arrays.asList(s1, s2);
    for (final Object s : list) {
      Integer count = map.get(s);
      if (null != count) {
        count++;
      } else {
        count = 1;
      }
      map.put(s, count);
    }

    System.out.println("What's in the map: " + map);

    assertEquals("Number distinct objects in map should be 1", 1, map.size());
  }
}
