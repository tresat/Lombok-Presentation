package com.tomtresansky.lombokpresentation.agent01.helloworldstatic;

import java.lang.instrument.Instrumentation;

/**
 * Simplest possible agent: prints a hello world message to demonstrate it
 * works.
 */
public class HelloWorldAgent {
  public static void premain(final String agentArgument, final Instrumentation instrumentation) {
    System.out.println("premain() method of " + HelloWorldAgent.class.getName() + " running.");
    System.out.println("Hello World!");
  }
}
