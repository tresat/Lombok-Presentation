package com.tomtresansky.lombokpresentation.agent02.dynamicload;

import java.lang.instrument.Instrumentation;

/**
 * Dynamically loaded agent modifies class files as they are loaded.
 */
public class DynamicallyLoadedAgent {
  /**
   * Dynamically-loaded agents use agentmain, NOT premain.
   */
  public static void agentmain(final String agentArgument, final Instrumentation instrumentation) {
    // Attach a transformer to have it run transformations
    instrumentation.addTransformer(new PrintingTransformer());
  }
}
