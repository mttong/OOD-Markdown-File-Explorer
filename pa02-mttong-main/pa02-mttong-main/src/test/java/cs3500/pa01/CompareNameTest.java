package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.comparators.CompareName;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.junit.jupiter.api.Test;

/**
 * compare name lexicographically
 */
public class CompareNameTest {

  /**
   * test method
   *
   * @throws IOException test from create Attribute
   */
  @Test
  public void testCompare() throws IOException {
    CompareName comparator = new CompareName();

    MarkdownFile md1 =
        createMarkdown(Path.of("src", "test", "resources", "SampleFileInputs", "arrays.md"));
    MarkdownFile md2 =
        createMarkdown(Path.of("src", "test", "resources", "SampleFileInputs", "vectors.md"));


    // Test when md1's filename is lexicographically before md2's filename
    int result = comparator.compare(md1, md2);
    assertTrue(result < 0);

    int result2 = comparator.compare(md2, md1);
    // Test when md2's filename is lexicographically after md1's filename
    assertTrue(result2 > 0);

  }

  /**
   * helper method to create Markdown file
   *
   * @param path String to path of markdown file
   * @return MarkdownFile
   * @throws IOException throws exception for readAttributes
   */
  private MarkdownFile createMarkdown(Path path) throws IOException {
    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
    return new MarkdownFile(path, path.getFileName().toString(), attr.creationTime(),
        attr.lastModifiedTime());
  }
}