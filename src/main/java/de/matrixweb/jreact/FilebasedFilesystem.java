package de.matrixweb.jreact;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilebasedFilesystem implements Filesystem {
  private static final Logger _log = LogManager.getLogger(FilebasedFilesystem.class);

  private final String fileBase;

  public FilebasedFilesystem() {
    this(".");
  }

  public FilebasedFilesystem(final String fileBase) {
    this.fileBase = fileBase;
  }

  @Override
  public boolean isFile(final String fileName) {
    _log.debug("Looking for " + fileName);
    return new File(this.fileBase, fileName.toString()).isFile();
  }

  @Override
  public String readFile(final String path) throws FileNotFoundException {
    _log.debug("Read " + path);
    try (Scanner scanner = new java.util.Scanner(new java.io.File(this.fileBase, path), "UTF-8")) {
      return scanner.useDelimiter("\\Z").next().toString();
    }
  }

  @Override
  public String getDirectory(final String path) throws FileNotFoundException {
    final File dir = new File(this.fileBase, path);
    return dir.isDirectory() ? dir.getName() : dir.getParent();
  }

  @Override
  public String getFilename(final String path) throws FileNotFoundException {
    return new File(this.fileBase, path).getName();
  }

  @Override
  public boolean exists(final String fileName) {
    _log.debug("Exists " + fileName);
    return new File(fileName).exists();
  }

}
