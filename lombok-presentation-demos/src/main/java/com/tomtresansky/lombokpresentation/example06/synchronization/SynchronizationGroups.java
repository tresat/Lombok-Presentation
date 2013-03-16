package com.tomtresansky.lombokpresentation.example06.synchronization;

import lombok.Getter;
import lombok.Synchronized;

/**
 * You can also specify an existing field to lock on in the argument.
 */
public class SynchronizationGroups {
  @Getter
  private Integer count1, count2;

  @Synchronized("count1")
  public void incrementCount1() {
    count1++;
  }

  @Synchronized("count1")
  public void doubleCount1() {
    count1 *= 2;
  }

  @Synchronized("count2")
  public void incrementCount2() {
    count2++;
  }

  @Synchronized("count2")
  public void doubleCount2() {
    count2 *= 2;
  }

  /*
   * Synchronizing on a field which doesn't exist causes an error to be flagged,
   * to keep string references to field names refactor-safe.
   * 
   * Uncomment the below to see this.
   */
  //  @Synchronized("blah")
  //  public void otherMethod() {}
}
