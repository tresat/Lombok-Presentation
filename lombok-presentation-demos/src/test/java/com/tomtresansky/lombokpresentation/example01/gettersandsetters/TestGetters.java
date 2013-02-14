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
    System.out.printf("Methods of %s:\n\n", g.getClass().getName());
    for (final Method m : Getters.class.getMethods()) {
      System.out.printf("%s\n", m.toGenericString());
    }

    // Print its name via call to generated getter
    System.out.printf("\nCalling generated getName() method: %s", g.getName());

    // Annotations of getName()?
    final Method gn = g.getClass().getMethod("getName");
    System.out.printf("\n\nAnnotations of getName():\n");
    for (final Annotation a : gn.getAnnotations()) {
      System.out.printf("%s", a.annotationType()); // Should print nothing!  No annotations on generated method
    }
  }
}
