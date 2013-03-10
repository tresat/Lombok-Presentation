package com.tomtresansky.lombokpresentation.example05.constructorannotations;

import java.util.Arrays;
import java.util.Collection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Lombok will generate a public, no-argument constructor for this class
 * (equivalent to what is implicitly added during compilation).
 */
@NoArgsConstructor
class GenerateConstructors {
  private double doubleVal;

  private int intVal;
  private String stringVal;
  private Collection<String> stringCollection;

  /*
   * But lombok requires explicit values for final fields. So if we uncomment
   * this line below, compiler error is flagged.
   * 
   * Technically, this is OK in non-lomboked java, just gets permanently set to
   * null implicitly, but lombok considers it uncool.
   */
  //private final int needAVal;
}

/**
 * Lombok will generate a protected constructor for all non-final, non-nullable
 * fields in this class.
 */
@RequiredArgsConstructor(access=AccessLevel.PROTECTED)
class GenerateConstructors2 {
  private final int intVall;
  private final String stringVal;
  private final Collection<String> stringCollection;

  // Non-final field is NOT required
  private double doubleValNonFinal;

  // Non nullable field is required
  @NonNull
  private Long longValNonNull;

  public static final void main(final String... args) {
    final GenerateConstructors2 instance = new GenerateConstructors2(3, "String", Arrays.asList("a", "b", "c"), 90000000000L);

    /*
     * Since field marked @NonNull, passing a null value causes runtime
     * exception when generated null-check fails if line below is uncommented.
     */
    // final GenerateConstructors2 instance2 = new GenerateConstructors2(3, "String", Arrays.asList("a", "b", "c"), null);
  }
}

/**
 * Lombok will generate a static factory method here, which calls a generated
 * private constructor.
 */
@AllArgsConstructor(staticName="of")
class GenerateConstructors3 {
  private final int intVall;
  private final String stringVal;
  private final Collection<String> stringCollection;

  // All args are included, whether final or not
  private double doubleValNonFinal;

  @NonNull
  private long longValNonNull;

  public static final void main(final String... args) {
    final GenerateConstructors3 intstance = GenerateConstructors3.of(4, "string", Arrays.asList("abc"),  5.6, 900000000L);
  }
}
