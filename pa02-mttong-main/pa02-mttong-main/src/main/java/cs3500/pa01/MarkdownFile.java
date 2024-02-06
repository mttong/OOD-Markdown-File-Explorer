package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * MarkdownFile class to represent "".md
 */
public class MarkdownFile {
  private final Path path;
  private final String filename;
  private final FileTime dateModified;
  private final FileTime dateCreated;

  /**
   * constructor
   *
   * @param p  path
   * @param fn filename
   * @param dm datemodified
   * @param dc datecreated
   */
  public MarkdownFile(Path p, String fn, FileTime dm, FileTime dc) {
    this.path = p;
    this.filename = fn;
    this.dateModified = dm;
    this.dateCreated = dc;
  }

  /**
   * is MarkdownFile?
   *
   * @return boolean
   */
  public boolean isMarkdownFile() {
    return filename.toLowerCase().endsWith(".md");
  }

  /**
   * gets the content of the Markdown file
   *
   * @return String of Markdown file contents
   */
  public String getContent() {
    return Utils.getContent(path);
  }

  /**
   * getter for filename
   *
   * @return String filename
   */
  public String getFilename() {

    return filename; //immutable
  }

  /**
   * getter for date modified
   *
   * @return FileTime datemodified
   */
  public FileTime getDateModified() {

    return dateModified; //immutable
  }

  /**
   * getter for date created
   *
   * @return FileTime datecreated
   */
  public FileTime getDateCreated() {

    return dateCreated; //immutable
  }

}
