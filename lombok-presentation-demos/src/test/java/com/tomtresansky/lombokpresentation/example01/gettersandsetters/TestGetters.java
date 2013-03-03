package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Demonstrates usage of {@link Getter}.
 */
public class TestGetters {
  public static void main(final String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {
    // Create a new Getters object
    final Getters g = new Getters("Stanley");

    // Print its name via call to generated getter
    System.out.printf("*** Calling generated getFirstName() method: %s\n", g.getFirstName());
    System.out.println("*** Done.");

    // Use reflection to spit out list of class's methods
    System.out.printf("\n*** Public Methods of %s:\n", g.getClass().getName());
    for (final Method m : Getters.class.getMethods()) {
      System.out.printf("%s\n", m.toGenericString());
    }
    System.out.println("*** Done.");

    // Use reflection to spit out list of class's fields
    System.out.printf("\n*** Public Fields of %s:\n", g.getClass().getName());
    for (final Field f : Getters.class.getFields()) {
      System.out.println(f);
    }
    System.out.println("*** Done.");


    // Is there anything different about the firstName field?  Does it still have the annotation?
    final Field fn = Getters.class.getField("firstName");
    System.out.printf("\n*** Annotations of firstName field:\n");
    for (final Annotation a : fn.getAnnotations()) {
      System.out.printf("%s\n", a.annotationType()); // Should print nothing!  No annotations left on the field.
    }
    System.out.println("*** Done.");

    // Is there anything different about the lombok-ed getFirstName() method?  Does it have the annotation?
    final Method gfn = Getters.class.getMethod("getFirstName");
    System.out.printf("\n*** Annotations of getFirstName():\n");
    for (final Annotation a : gfn.getAnnotations()) {
      System.out.printf("%s\n", a.annotationType()); // Should print nothing!  No annotations on generated method.
    }
    System.out.println("*** Done.");
  }
}

/*
 * To demo outside of eclipse.
 * 
 * Be sure to have run Maven test build beforehand to generate classes!
 * 
 * cd "C:\Users\Tom\Programming\Projects\lombok-presentation\lombok-presentation-demos"
 * 
 * java -cp "target\classes;target\test-classes" com.tomtresansky.lombokpresentation.example01.gettersandsetters.TestGetters
 */
