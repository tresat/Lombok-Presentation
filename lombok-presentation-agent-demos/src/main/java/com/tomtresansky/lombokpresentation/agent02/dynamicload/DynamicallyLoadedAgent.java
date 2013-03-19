package com.tomtresansky.lombokpresentation.agent02.dynamicload;

import java.lang.instrument.Instrumentation;

public class DynamicallyLoadedAgent {
  public static void agentmain(final String agentArgument, final Instrumentation instrumentation) {
    instrumentation.addTransformer(new ModifyingTransformer());
  }
}
