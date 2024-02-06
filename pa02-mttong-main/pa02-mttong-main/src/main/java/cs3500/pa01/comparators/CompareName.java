package cs3500.pa01.comparators;

import cs3500.pa01.MarkdownFile;
import java.util.Comparator;

/**
 * Comparator for filenames
 */
public class CompareName implements Comparator<MarkdownFile> {
  /**
   * compare method
   *
   * @param md1 the first object to be compared.
   * @param md2 the second object to be compared.
   * @return int
   */
  public int compare(MarkdownFile md1, MarkdownFile md2) {
    String name1 = md1.getFilename();
    String name2 = md2.getFilename();
    return name1.compareTo(name2);
  }
}