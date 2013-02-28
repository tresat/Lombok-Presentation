package com.tomtresansky.lombokpresentation.example01.gettersandsetters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This class contains various demos of @Getter and @Setter features.
 */
public class MoreGettersAndSetters {
  /*
   * Lombok's annotations aid in READABILITY - not just save time typing.
   * 
   * For instance, it's easy to see the one "interesting" piece of functionality
   * when Lombok builds getters and setters on this class...
   */
  private static class SongLombok1 {
    @Getter
    @Setter
    private String artist;

    @Getter
    @Setter
    private int track;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String album;

    /**
     * @return human-readable description of the song
     */
    public String getDescription() {
      return artist + " - " + album +  " (" + track + ") " + title;
    }
  }

  /*
   * ...than it is on this one, which produces functionally identical byte-code,
   * where Eclipse created them via rightclick, Source -> Generate Getters and
   * Setters.
   */
  private static class SongEclipse1 {
    private String artist;
    private int track;
    private String title;
    private String album;

    /**
     * @return the album
     */
    public String getAlbum() {
      return album;
    }
    /**
     * @param album the album to set
     */
    public void setAlbum(final String album) {
      this.album = album;
    }
    /**
     * @return the artist
     */
    public String getArtist() {
      return artist;
    }
    /**
     * @param artist the artist to set
     */
    public void setArtist(final String artist) {
      this.artist = artist;
    }
    /**
     * @return the track
     */
    public int getTrack() {
      return track;
    }
    /**
     * @param track the track to set
     */
    public void setTrack(final int track) {
      this.track = track;
    }
    /**
     * @return the title
     */
    public String getTitle() {
      return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(final String title) {
      this.title = title;
    }

    /**
     * @return human-readable description of the song
     */
    public String getDescription() {
      return artist + " - " + album +  " (" + track + ") " + title;
    }
  }

  /*
   * These annotations can also be used on the class directly, which applies
   * them to all non-static fields. This yields great information density - this
   * class is also equivalent to the above classes!
   */
  @Getter
  @Setter
  private static class SongLombok2 {
    private String artist, title, album;
    private int track; // change me to final or static and the generated setter disappears

    /**
     * @return human-readable description of the song
     */
    public String getDescription() {
      return artist + " - " + album +  " (" + track + ") " + title;
    }
  }

  /*
   * Can specify access modifiers for generated methods.
   */
  private static class Turtle {
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private String name;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int weight;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PRIVATE)
    private String color;
  }

  /*
   * Can override class-level annotations at field-level: make everything
   * public (the default) except for X.
   */
  @Getter
  @Setter
  private static class Turtle2 {
    private String name;
    private int weight;
    private String color;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.NONE) // Or tell lombok NOT to generate a method
    private boolean ninja;
  }

  /*
   * Lombok respects methods already in place, in cases when special processing is required.
   */
  @Getter
  @Setter
  private static class Turtle3 {
    private String name;
    private int weight;
    private String color;

    public int getWeight() {
      if ("red".equals(color)) {
        return -50000;
      } else {
        return weight;
      }
    }

    /*
     * Run me to demonstrate custom getter method.
     */
    public static void main(final String... args) {
      final Turtle3 leonardo = new Turtle3();
      leonardo.setName("Leonardo");
      leonardo.setWeight(100);
      leonardo.setColor("blue");

      final Turtle3 raphael = new Turtle3();
      raphael.setName("Raphael");
      raphael.setWeight(110);
      raphael.setColor("red");

      ninjaTest(leonardo);
      ninjaTest(raphael);
    }

    private static void ninjaTest(final Turtle3 turtle) {
      System.out.println(turtle.getName() + " (" + turtle.getColor() + ") weight " + turtle.getWeight() + " pounds.");
    }
  }
}
