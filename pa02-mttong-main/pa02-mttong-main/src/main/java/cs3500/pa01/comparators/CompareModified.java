package cs3500.pa01.comparators;

import cs3500.pa01.MarkdownFile;
import java.util.Comparator;

/**
 * Comparator for last modified time
 */
public class CompareModified implements Comparator<MarkdownFile> {
  /**
   * compare method
   *
   * @param md1 the first object to be compared.
   * @param md2 the second object to be compared.
   * @return int
   */
  public int compare(MarkdownFile md1, MarkdownFile md2) {
    return md1.getDateModified().compareTo(md2.getDateModified());
  }
}