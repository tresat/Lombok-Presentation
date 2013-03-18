package com.tomtresansky.lombokpresentation.agent02.dynamicload;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.tools.attach.VirtualMachine;

/**
 * This class demonstrates starting an agent dynamically (attaching to an
 * already running JVM which wasn't started with the -javaagent VM argument).
 */
public class TestDynamicAgent {
  public static void main(final String[] args) throws Exception {
    // Grab the PID from running JVM
    final String pid = getJVMProcessId();

    /*
     * com.sun.tools.attach.VirtualMachine class is contained in tools.jar in
     * the \lib folder of the JDK.
     * 
     * tools.jar is not provided in any public Maven repository, (and is not by
     * default included in the eclipse execution environment with the rest of
     * the JDK libs, so have added the copy from JDK 1.7.0_11 to the project in
     * lib\main directory as a system dependency in the Maven build, and added
     * it to the launch classpath.
     */
    final VirtualMachine vm = VirtualMachine.attach(pid);

    final File curDir = new File(TestDynamicAgent.class.getProtectionDomain().getCodeSource().getLocation().toURI());
    final Path agentJar = Paths.get(curDir.getAbsolutePath(), "lombok-presentation-agent-demos-0.0.1-SNAPSHOT-agent02.jar");
    System.out.println(agentJar);

    vm.loadAgent(agentJar.toString());
  }

  /**
   * Abstracts the process of getting the PID of the JVM this class is running
   * within.
   * <p>
   * May fail in some JVM implementations. Hasn't failed for me yet.
   * 
   * @return the process ID of the running JVM
   */
  private static String getJVMProcessId() {
    /*
     * Something like '<pid>@<hostname>' will be returned at least in SUN /
     * Oracle / OpenJDK JVMs.
     */
    final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
    final int index = jvmName.indexOf('@');

    if (index < 1) {
      // part before '@' empty (index = 0) / '@' not found (index = -1)
      throw new RuntimeException("JVM name not in expected format (<pid>@<hostname>): " + jvmName);
    }

    // Parse the PID as a long (to make sure we actualy got one) but then return it as a String
    try {
      return Long.toString(Long.parseLong(jvmName.substring(0, index)));
    } catch (final NumberFormatException e) {
      throw new RuntimeException("Errot parsing PID from JVM name: " + jvmName);
    }
  }
}
