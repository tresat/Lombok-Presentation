package com.tomtresansky.lombokpresentation.agent03.redefiner;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Redefining Agent can change already loaded classes.
 */
public class RedefiningAgent {
  /**
   * Dynamically-loaded agents use agentmain, NOT premain.
   */
  public static void agentmain(final String agentArgument, final Instrumentation instrumentation) throws UnmodifiableClassException {
    // Attach a transformer to have it run transformations
    instrumentation.addTransformer(new PrintingTransformer(), true);

    // And re-transform all loaded, modifiable classes
    for (final Class<?> klass : instrumentation.getAllLoadedClasses()) {
      if (instrumentation.isModifiableClass(klass)) {
        instrumentation.retransformClasses(new Class<?>[]{klass});
      }
    }
  }
}
