package com.tomtresansky.lombokpresentation.agent03.redefiner;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.tools.attach.VirtualMachine;

/**
 * Same test driver code as agent 2 demo, except loads agent 3's jar.
 * 
 * Agent 3's jar can redefine loaded classes, so output will vary.
 */
public class TestDynamicAgent {
  public static void main(final String[] args) throws Exception {
    System.out.println("Creating Printer1 instance and calling print() a few times...");
    final Printer1 p1 = new Printer1("Sam");
    for (int i = 0; i < 3; i++) {
      p1.print();
    }
    System.out.println("Done with Printer1.\n");

    System.out.println("Attaching agent dynamically...");
    attachAgent();
    System.out.println("Done attaching agent.\n");

    System.out.println("Creating Printer2 instance and calling print()...");
    final Printer2 p2 = new Printer2("Max");
    for (int i = 0; i < 3; i++) {
      p2.print();
    }
    System.out.println("Done with Printer2.\n");

    System.out.println("Creating another Printer1 instance and calling print() a few times...");
    final Printer1 p3 = new Printer1("Sam");
    for (int i = 0; i < 3; i++) {
      p3.print();
    }
  }

  /**
   * Abstracts the process of getting the PID of the JVM this class is running
   * within.
   * <p>
   * May fail in some JVM implementations. Hasn't failed for me yet.
   * 
   * @return the process ID of the running JVM
   */
  private static void attachAgent() throws Exception {
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
    final Path agentJarPath = Paths.get(curDir.getParent(), "lombok-presentation-agent-demos-0.0.1-SNAPSHOT-agent03.jar");

    vm.loadAgent(agentJarPath.toString());
    vm.detach();
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
      throw new RuntimeException("Error parsing PID from JVM name: " + jvmName);
    }
  }
}
