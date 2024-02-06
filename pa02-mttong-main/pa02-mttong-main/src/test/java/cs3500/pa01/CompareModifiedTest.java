package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.comparators.CompareModified;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * compare modified time test
 */
public class CompareModifiedTest {


  /**
   * test method
   *
   * @throws IOException setLastModifiedTime exception
   */
  @Test
  public void testCompare() throws IOException {

    Path p1 = Path.of("src", "test", "resources", "SampleFileInputs", "arrays.md");
    Path p2 = Path.of("src", "test", "resources", "SampleFileInputs", "vectors.md");

    Files.setLastModifiedTime(p1, FileTime.from(Instant.now()));
    Files.setLastModifiedTime(p2, FileTime.from(Instant.now().minusSeconds(10)));

    CompareModified comparator = new CompareModified();

    MarkdownFile md1 = createMarkdown(p1);
    MarkdownFile md2 = createMarkdown(p2);

    // Test when md1's filename is lexicographically before md2's filename
    int result = comparator.compare(md1, md2);
    assertTrue(result > 0);

    int result2 = comparator.compare(md2, md1);
    // Test when md2's filename is lexicographically after md1's filename
    assertTrue(result2 < 0);


  }

  /**
   * helper method to create Markdown files
   *
   * @param path path of sample file
   * @return returns a new Markdown file created based on the path
   * @throws IOException exception for readAttributes
   */
  private MarkdownFile createMarkdown(Path path) throws IOException {
    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
    return new MarkdownFile(path, path.getFileName().toString(), attr.creationTime(),
        attr.lastModifiedTime());
  }
}
