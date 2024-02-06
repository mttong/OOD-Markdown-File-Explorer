package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Markdown Collector to get all Markdown Files in directory
 */
public class MarkdownCollector implements FileVisitor<Path> {
  private final ArrayList<MarkdownFile> mdList;

  /**
   * constructor
   */
  public MarkdownCollector() {
    this.mdList = new ArrayList<>();
  }

  /**
   * getter for the list of Markdown Files
   *
   * @return ArrayList of MarkdownFiles
   */
  public ArrayList<MarkdownFile> getMarkdownFiles() {
    return new ArrayList<>(mdList);   // return mdList returns a reference so immutable
    // new ArrayList returns a copy
  }

  /**
   * visits each file
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return CONTINUE
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (attr.isRegularFile()) {
      //creating Markdown File
      MarkdownFile md = new MarkdownFile(file, file.getFileName().toString(), attr.creationTime(),
          attr.lastModifiedTime());
      if (md.isMarkdownFile()) {
        mdList.add(md);
      }
    }
    return CONTINUE;
  }

  /**
   * postVisitDirectory
   *
   * @param dir  a reference to the directory
   * @param exec {@code null} if the iteration of the directory completes without
   *             an error; otherwise the View/O exception that caused the iteration
   *             of the directory to complete prematurely
   * @return CONTINUE
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    return CONTINUE;
  }

  /**
   * preVisitDirectory
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return CONTINUE
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;

  }

  /**
   * file cannot be visited
   *
   * @param file a reference to the file
   * @param exc  the View/O exception that prevented the file from being visited
   * @return CONTINUE
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    return CONTINUE;
  }

}
