package com.tomtresansky.lombokpresentation.example08.sneakythrows;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.activation.ActivateFailedException;
import java.rmi.activation.ActivationException;
import java.rmi.activation.UnknownGroupException;
import java.rmi.activation.UnknownObjectException;

import lombok.SneakyThrows;

public class SneakyThrowsUseCases {
  /**
   * This exception thrown by the String constructor is literally impossible.
   * 
   * {@link String#String(byte[], String)} declares that it can throw an
   * {@link UnsupportedEncodingException} BUT according to the JVM specification, UTF-8
   * must always be available!!!
   * 
   * So if this encoding ISN'T AVAILABLE, your JVM shouldn't even startup!
   */
  @SneakyThrows(UnsupportedEncodingException.class)
  public String impossibleToOccur(final byte[] bytes) {
    return new String(bytes, "UTF-8");
  }

  /**
   * This implementation of runnable wants to throw an IOException...but it
   * CAN'T because IO Exception isn't declared in {@link Runnable#run()}.
   * 
   * SneakyThrows lets us throw it directly, avoiding the need to wrap in some
   * kind of runtime exception.
   * 
   * This can be a problem with anonymous classes, especially the kind of
   * anonymous interface implementations that stand-in for method as first class
   * objects.
   */
  public static class RestrictiveInterface implements Runnable {
    @SneakyThrows(IOException.class) // comment me out and error: can't throw IOException non-sneakily
    @Override
    public void run() {
      throw new IOException();
    }
  }

  /**
   * In real-life, despite the author's best intensions, any use of the
   * {@link OverlySpecificInterface#doSomething()} method will get wrapped in a
   * try-catch, with a Runtime Exception popping out if anything fails.
   * 
   * If the method calling doSomething just sneakily throws these exceptions, we
   * save a layer of indirection and have less, cleaner source to maintain.
   */
  public interface OverlySpecificInterface {
    public void doSomething() throws ActivationException, ActivateFailedException, UnknownGroupException, UnknownObjectException;
  }
}
