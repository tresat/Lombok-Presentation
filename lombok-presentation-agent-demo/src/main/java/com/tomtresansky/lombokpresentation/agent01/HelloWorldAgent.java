package com.tomtresansky.lombokpresentation.agent01;

import java.lang.instrument.Instrumentation;

public class HelloWorldAgent {
  public static void premain(final String agentArgument, final Instrumentation instrumentation) {
    System.out.println("Test Java Agent");
  }
}
