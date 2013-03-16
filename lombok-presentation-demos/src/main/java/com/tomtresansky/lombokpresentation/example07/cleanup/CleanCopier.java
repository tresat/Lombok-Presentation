package com.tomtresansky.lombokpresentation.example07.cleanup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.Cleanup;

/**
 * Demonstrating equivalent resource close code in java 6, java 7 and with lombok.
 * 
 * Some simple copy to do a binary file copy from one file to another.
 */
public class CleanCopier {
  public static void copyWithJava6(final String inFile, final String outFile) throws IOException {
    final InputStream in = new FileInputStream(inFile);

    try {
      final OutputStream out = new FileOutputStream(outFile);

      try {
        final byte[] b = new byte[10000];

        while (true) {
          final int r = in.read(b);
          if (r == -1) {
            break;
          }
          out.write(b, 0, r);
        }
      } finally {
        if (out != null) {
          out.close();
        }
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  public static void copyWithJava7(final String inFile, final String outFile) throws IOException {
    try(final InputStream in = new FileInputStream(inFile);
        final OutputStream out = new FileOutputStream(outFile)) {
      final byte[] b = new byte[10000];

      while (true) {
        final int r = in.read(b);
        if (r == -1) {
          break;
        }
        out.write(b, 0, r);
      }
    }
  }

  public static void copyWithLombok(final String inFile, final String outFile) throws IOException {
    @Cleanup final InputStream in = new FileInputStream(inFile);
    @Cleanup final OutputStream out = new FileOutputStream(outFile);

    final byte[] b = new byte[10000];
    while (true) {
      final int r = in.read(b);
      if (r == -1) {
        break;
      }
      out.write(b, 0, r);
    }
  }
}
