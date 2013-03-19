package com.tomtresansky.lombokpresentation.example09.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import lombok.val;

/**
 * This class demonstrates the usage of lombok's {@link val}.
 */
public class InferTypes {
  public static void main(final String... args) {
    // Infer a type
    val str = new String("Tom");
    str.length(); // call a method on it

    val str2 = "Joe"; // string literals are fine
    System.out.println("\nInferred strings values: " + str + ", " + str2);
    System.out.println("Inferred strings types: " + str.getClass() + ", " + str2.getClass());

    // Inferred primitives work with primitives
    val i = 5;
    val j = i + 5;
    final int k = j * 2;
    System.out.println("\nInferred int value: " + i);

    // i is inferred as a primitive, so you can't call methods on it and the below will break
    //i.hashCode();

    // Can't change an inferred type variable's value: they are implicitly final, below causes error
    // val x = "a";
    // x = "b";

    // Infer plays nice with generics
    val list = new ArrayList<Integer>();
    list.add(10);
    list.add(20);
    list.add(30);
    System.out.println("\nIterating our inferred list:");
    for (final int item : list) {
      System.out.println(item);
    }

    // Infer gets the MOST-SPECIFIC type it can, so list is an ARRAYLIST, NOT a LIST
    System.out.println("\nA List might not be random access, but we can access the item at index 2 of our inferred ArrayList directly using get(2): " + list.get(2));

    // Can iterate through an inferred type, using an inferred type!
    val map = new HashMap<Integer, String>();
    map.put(1, "Barry");
    map.put(2, "Harry");
    map.put(3, "Larry");
    System.out.println("\nIterating an inferred map with an interred element variable:");
    // The generics on the type of item can quickly get brutal for maps of collections, etc., so just val it
    for (val item : map.entrySet()) {
      System.out.println(item);
    }

    // Most specific common superclass is used in case of compound types unknown until runtime
    // r is typed as an AbstractCollection
    final int r = new Random().nextInt();
    val whatAmI = (r % 2 == 0 ? new HashSet<String>() : new ArrayList<String>());
    whatAmI.add("a");
    whatAmI.add("b");
    // whatAmI is typed as AbstractCollection (check auto-complete on whatAmI)

    // Val not super smart about compound types, even though eclipse knows to flag dead code
    val whatAmI2 = (false ? new HashSet<String>() : new ArrayList<String>());
    whatAmI2.add("a");
    whatAmI2.add("b");

    // Doesn't work: whatAmI2 is typed as AbstractCollection, will always be an ArrayList though
    //whatAmI2.get(0);

    // Null initializers make Object-typed variables (check auto-complete on n)
    val n = null;
    // They also aren't assignable, so aren't very useful
    // n = "new";
  }
}
