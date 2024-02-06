package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * test markdown collector class
 */
public class MarkdownCollectorTest {

  /**
   * getter for the markdown files
   *
   * @throws IOException exception for walk file tree
   */
  @Test
  public void testGetMarkdownFiles() throws IOException {
    MarkdownCollector mdc = new MarkdownCollector();

    Files.walkFileTree(Path.of("src", "test", "resources", "SampleFileInputs"), mdc);


    // get list of traversed Markdown file paths
    List<MarkdownFile> actualFiles = mdc.getMarkdownFiles();

    // compare both lists
    assertEquals(2, actualFiles.size());
  }


  /**
   * tests if file isn't able to get visited
   */
  @Test
  public void testVisitFileFailed() {
    MarkdownCollector mdc = new MarkdownCollector();
    Path path = Path.of("src", "test", "resources", "SampleFileInputs");
    assertEquals(mdc.visitFileFailed(path, new IOException()), CONTINUE);
  }

}
