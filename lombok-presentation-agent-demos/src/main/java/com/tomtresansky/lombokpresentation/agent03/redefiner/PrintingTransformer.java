package com.tomtresansky.lombokpresentation.agent03.redefiner;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * This class uses the ASM bytecode manipulation library to redefine all methods
 * named "print" in any classes in a package beginning with "com.tomtresansky...Printer".
 * 
 * It adds an additional output statement to the print method.
 */
public class PrintingTransformer implements ClassFileTransformer {
  @Override
  public byte[] transform(final ClassLoader loader, final String className,
      final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain,
      final byte[] classfileBuffer) throws IllegalClassFormatException {
    // Class name passed as param, only modify certain classes
    if (className.startsWith("com/tomtresansky/lombokpresentation/agent03/redefiner/Printer")) {
      System.out.println("-Redefining Class: " + className);

      final ClassWriter cw = new ClassWriter(0);
      final ClassVisitor ca = new MyClassAdapter(cw);
      final ClassReader cr = new ClassReader(classfileBuffer);

      cr.accept(ca, 0);
      return cw.toByteArray();
    } else {
      return null;
    }
  }

  /**
   * Class adapter uses ASM bytecode manipulation to insert method call.
   */
  public class MyClassAdapter extends ClassNode implements Opcodes {
    private ClassVisitor cv;

    public MyClassAdapter(final ClassVisitor cv) {
      this.cv = cv;
    }

    @Override
    public void visitEnd() {
      @SuppressWarnings("unchecked")
      final List<MethodNode> methodNodes = methods;

      for (final MethodNode mn : methodNodes) {
        if (mn.name.equals("print")) {
          System.out.println("--Redefining Method: " + mn.name);

          final InsnList il = new InsnList();
          il.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
          il.add(new LdcInsnNode("Where did this print come from?"));
          il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
          mn.instructions.insert(il);

          mn.maxStack += 2;
        }
      }

      accept(cv);
    }
  }
}
