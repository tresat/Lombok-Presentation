package com.tomtresansky.lombokpresentation.example10.extensionmethod;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import lombok.experimental.ExtensionMethod;

/**
 * This class defines methods to add to the String class.
 */
class StringExtensions {
  /**
   * Checks if a string is non-null and non-empty.
   */
  public static boolean hasText(final String s) {
    return s != null && !s.isEmpty();
  }

  /**
   * Checks if a string begins and ends with the a certain letter.
   */
  public static boolean bookendedBy(final String s, final Character bookend) {
    /*
     * Extension method class can't directly access extension methods on
     * modified type, so we can't do s.hasText().
     */
    return hasText(s) && s.startsWith(bookend.toString()) && s.endsWith(bookend.toString());
  }
}

/**
 * This class uses the extended version of String, which features the additional
 * methods we defined in {@link StringExtensions}.
 */
@ExtensionMethod(StringExtensions.class)
class DemoExtensionMethods {
  public static void main(final String... args) {
    final String s = "The duck flies at midnight!";
    final String s2 = "AbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbA";

    System.out.println("S has text: " + s.hasText());
    System.out.println("S2 is bookended by 'A': " + s2.bookendedBy('A'));

    // Because these calls are actually transformed into static calls by lombok, this DOESN'T throw an NPE
    final String s3 = null;
    System.out.println("S3 has text: " + s3.hasText());

    /*
     * Also, lombok has left eclipse's ability to detect bad ideas intact. If we
     * uncomment the below, it will be flagged as a warning, since s3 must be
     * null, and a non-extension method will fail with an NPE every time.
     */
    //s3.startsWith("A");
  }
}

/**
 * Can also extend every class!
 */
class ObjectExtensions {
  /**
   * Provides a substitute, for if this instance is null.
   */
  public static <T> T or(final T object, final T ifNull) {
    return object != null ? object : ifNull;
  }
}

@ExtensionMethod(ObjectExtensions.class)
class DemoExtensionMethods2 {
  public static void main(final String... args) {
    final String s = null;
    final List<String> l = null;
    final Color c = null;

    // Print s, or if its null, print the string we've specified
    System.out.println(s.or("Extension"));

    // Called on a non-null, uses calling instance
    System.out.println("methods".or("won't print"));

    // Assign l to l2, or if l is null, use an alternate list
    final List<String> l2 = l.or(Arrays.asList("are"));
    System.out.println(l2);

    // Can even substitute different types (Generic T has no restrictions, so T uses Object)
    System.out.println(c.or("awesome!"));
  }
}

