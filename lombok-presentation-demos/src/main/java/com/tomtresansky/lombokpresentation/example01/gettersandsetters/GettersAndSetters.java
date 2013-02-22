package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import lombok.Getter;
import lombok.Setter;

/**
 * This class contains various demos of @Getter and @Setter features.
 */
public class GettersAndSetters {
  /*
   * Lombok's annotations aid in READABILITY not just save time typing.
   * 
   * For instance, it's easy to see the one "interesting" piece of functionality when Lombok builds getters and setters on this class...
   */

  private class GettersAndSetters1Lombok {
    @Getter @Setter
    private String artist;

    @Getter @Setter
    private int track;

    @Getter @Setter
    private String title;
  }

  /*
   * ...than it is on this one, which produces functionally identical byte-code,
   * where Eclipse created them via rightclick, Source -> Generate Getters and
   * Setters.
   */
  private class GettersAndSetters1Eclipse {
    private String artist;
    private int track;
    private String title;
  }

}
