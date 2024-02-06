package cs3500.pa01.comparators;

import cs3500.pa01.MarkdownFile;
import java.util.Comparator;

/**
 * Comparator for creation time
 */
public class CompareCreated implements Comparator<MarkdownFile> {
  /**
   * compare method
   *
   * @param md1 the first object to be compared.
   * @param md2 the second object to be compared.
   * @return int
   */
  public int compare(MarkdownFile md1, MarkdownFile md2) {
    return md1.getDateCreated().compareTo(md2.getDateCreated());
  }
}
