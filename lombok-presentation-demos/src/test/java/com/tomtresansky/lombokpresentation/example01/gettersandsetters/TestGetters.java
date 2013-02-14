package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import lombok.Getter;

/**
 * Demonstrates usage of {@link Getter}.
 */
public class TestGetters {
  public static void main(final String[] args) throws NoSuchMethodException, SecurityException {
    // Create a new Getters object
    final Getters g = new Getters("Stanley");

    // Use reflection to spit out list of class's methods
    System.out.printf("*** Methods of %s:\n", g.getClass().getName());
    for (final Method m : Getters.class.getMethods()) {
      System.out.printf("%s\n", m.toGenericString());
    }
    System.out.println("***Done.");

    // Print its name via call to generated getter
    System.out.printf("\n***Calling generated getName() method: %s", g.getName());

    // Is there anything different about the lombok-ed getName() method?  Does it still have the annotation?
    final Method gn = g.getClass().getMethod("getName");
    System.out.printf("\n\n***Annotations of getName():\n");
    for (final Annotation a : gn.getAnnotations()) {
      System.out.printf("%s", a.annotationType()); // Should print nothing!  No annotations on generated method
    }
    System.out.println("***Done.");
  }
}
