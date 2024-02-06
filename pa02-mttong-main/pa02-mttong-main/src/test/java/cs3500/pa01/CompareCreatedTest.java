package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertFalse;

import cs3500.pa01.comparators.CompareCreated;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.junit.jupiter.api.Test;

/**
 * compare creation time test
 */
public class CompareCreatedTest {

  /**
   * test compare in CompareCreated class
   *
   * @throws IOException if createTempFile unable to be done
   */
  @Test
  public void testCompare() throws IOException {
    Path file1 = Files.createTempFile("file1", ".md");
    Path file2 = Files.createTempFile("file2", ".md");
    CompareCreated sortByDateCreated = new CompareCreated();
    int result = sortByDateCreated.compare(createMarkdown(file1), createMarkdown(file2));
    assertFalse(result > 0);
    Files.delete(file1);
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