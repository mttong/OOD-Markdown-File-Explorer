package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests markdown file class
 */
public class MarkdownFileTest {
  private Path testFilePath;
  private FileTime testDateModified;
  private FileTime testDateCreated;

  /**
   * set up markdown file before each test
   *
   * @throws IOException exception for create temp file
   */
  @BeforeEach
  public void setup() throws IOException {
    // Create a temporary file for testing
    testFilePath = Files.createTempFile("test", ".md");
    testDateModified = FileTime.from(Instant.now());
    testDateCreated = FileTime.from(Instant.now());

    // Write content to the temporary file
    String testContent = "This is a test content";
    Files.write(testFilePath, testContent.getBytes());
  }

  /**
   * tests if file is markdown file
   */
  @Test
  public void testIsMarkdownFile() {
    MarkdownFile markdownFile =
        new MarkdownFile(testFilePath, testFilePath.getFileName().toString(), testDateModified,
            testDateCreated);

    assertTrue(markdownFile.isMarkdownFile());
  }

  /**
   * tests if the contents of the markdown file was valid
   */
  @Test
  public void testGetContent() {
    MarkdownFile markdownFile =
        new MarkdownFile(testFilePath, testFilePath.getFileName().toString(), testDateModified,
            testDateCreated);

    String expectedContent = "This is a test content";
    String actualContent = markdownFile.getContent();

    assertEquals(expectedContent, actualContent);
  }

  /**
   * tests exception to getContent
   */
  @Test
  void testGetContentException() {
    Path nonExistentPath = Paths.get("nonexistents.txt");
    MarkdownFile markdownFile = new MarkdownFile(nonExistentPath, "nonexistents.txt", null, null);
    assertThrows(IllegalStateException.class, markdownFile::getContent,
        "IOException should be thrown when reading nonexistent file");
  }

  /**
   * tests filename of markdown
   */
  @Test
  public void testGetFilename() {
    MarkdownFile markdownFile =
        new MarkdownFile(testFilePath, testFilePath.getFileName().toString(), testDateModified,
            testDateCreated);

    String expectedFilename = testFilePath.getFileName().toString();
    String actualFilename = markdownFile.getFilename();

    assertEquals(expectedFilename, actualFilename);
  }

  /**
   * tests date modified of markdown
   */
  @Test
  public void testGetDateModified() {
    MarkdownFile markdownFile =
        new MarkdownFile(testFilePath, testFilePath.getFileName().toString(), testDateModified,
            testDateCreated);

    FileTime expectedDateModified = testDateModified;
    FileTime actualDateModified = markdownFile.getDateModified();

    assertEquals(expectedDateModified, actualDateModified);
  }

  /**
   * tests date created of markdown file
   */
  @Test
  public void testGetDateCreated() {
    MarkdownFile markdownFile =
        new MarkdownFile(testFilePath, testFilePath.getFileName().toString(), testDateModified,
            testDateCreated);

    FileTime expectedDateCreated = testDateCreated;
    FileTime actualDateCreated = markdownFile.getDateCreated();

    assertEquals(expectedDateCreated, actualDateCreated);
  }
}
