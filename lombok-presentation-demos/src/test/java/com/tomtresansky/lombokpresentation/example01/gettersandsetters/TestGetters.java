package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.Getter;

/**
 * Demonstrates usage of {@link Getter}.
 */
public class TestGetters {
  public static void main(final String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {
    // Create a new instance
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

    // Is there anything different about the firstName field?
    // Does it still have the annotation?
    final Field fn = Getters.class.getField("firstName");
    System.out.printf("\n*** Annotations of firstName field:\n");
    for (final Annotation a : fn.getAnnotations()) {
      // Should print nothing!  No annotations left on the field.
      System.out.printf("%s\n", a.annotationType());
    }
    System.out.println("*** Done.");

    // Is there anything different about the lombok-ed getFirstName() method?
    // Does it have the annotation?
    final Method gfn = Getters.class.getMethod("getFirstName");
    System.out.printf("\n*** Annotations of getFirstName():\n");
    for (final Annotation a : gfn.getAnnotations()) {
      // Should print nothing!  No annotations on generated method.
      System.out.printf("%s\n", a.annotationType());
    }
    System.out.println("*** Done.");
  }
}

/*
 * To demo outside of eclipse: Be sure to have run Maven test build beforehand to generate classes!
 * 
 * Enter directory from command line
cd C:\Users\Tom\Programming\Projects\lombok-presentation\lombok-presentation-demos
 * 
 * Show resulting class info generated from eclipse
javap -cp target\classes com.tomtresansky.lombokpresentation.example01.gettersandsetters.Getters
 * 
 * 
 * 
 * Now show compilation without eclipse: Be sure to first clear manuallycompile directory of all but lombok jar
 * 
 * Compile classes manually
javac -cp manuallycompilelib/lombok-0.11.6.jar;manuallycompile src/main/java/com/tomtresansky/lombokpresentation/example01/gettersandsetters/Getters.java -d manuallycompile
javac -cp manuallycompilelib/lombok-0.11.6.jar;manuallycompile src/test/java/com/tomtresansky/lombokpresentation/example01/gettersandsetters/TestGetters.java -d manuallycompile
 * 
 * Show resulting class info
javap -cp manuallycompile com.tomtresansky.lombokpresentation.example01.gettersandsetters.Getters
 * 
 * Run it!
java -cp manuallycompile com.tomtresansky.lombokpresentation.example01.gettersandsetters.TestGetters
 */
