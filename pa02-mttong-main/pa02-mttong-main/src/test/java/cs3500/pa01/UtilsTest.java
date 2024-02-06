package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.controller.Controller;
import cs3500.pa01.controller.FileCreationController;
import cs3500.pa01.controller.StudySessionController;
import cs3500.pa01.enums.OrganizationalFlag;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * tests util class
 */
class UtilsTest {

  /**
   * tests sort based on FILENAME organizational flag
   *
   * @throws IOException exception for createMarkdown
   */
  @Test
  public void testSortByFilename() throws IOException {
    MarkdownFile arrays =
        createMarkdown(Path.of("src", "test", "resources", "SampleFileInputs", "arrays.md"));
    MarkdownFile vectors =
        createMarkdown(Path.of("src", "test", "resources", "SampleFileInputs", "vectors.md"));

    ArrayList<MarkdownFile> mfs = new ArrayList<>();
    mfs.add(vectors);
    mfs.add(arrays);

    Utils.sort(OrganizationalFlag.FILENAME, mfs);

    ArrayList<MarkdownFile> expectedMfs = new ArrayList<>();
    expectedMfs.add(arrays);
    expectedMfs.add(vectors);

    for (int i = 0; i < mfs.size(); i++) {
      assertEquals(expectedMfs.get(i).getFilename(), mfs.get(i).getFilename());
      assertEquals(expectedMfs.get(i).getContent(), mfs.get(i).getContent());
      assertEquals(expectedMfs.get(i).getDateCreated(), mfs.get(i).getDateCreated());
      assertEquals(expectedMfs.get(i).getDateModified(), mfs.get(i).getDateModified());
    }

  }

  /**
   * tests sort based on MODIFIED organizational flag
   *
   * @throws IOException exception for createMarkdown
   */
  @Test
  public void testSortByModified() throws IOException {
    Path p1 = Path.of("src", "test", "resources", "SampleFileInputs", "arrays.md");
    Path p2 = Path.of("src", "test", "resources", "SampleFileInputs", "vectors.md");

    Files.setLastModifiedTime(p1, FileTime.from(Instant.now()));
    Files.setLastModifiedTime(p2, FileTime.from(Instant.now().minusSeconds(10)));

    MarkdownFile arrays = createMarkdown(p1);
    MarkdownFile vectors = createMarkdown(p2);

    ArrayList<MarkdownFile> mfs = new ArrayList<>();
    mfs.add(arrays);
    mfs.add(vectors);

    Utils.sort(OrganizationalFlag.MODIFIED, mfs);

    ArrayList<MarkdownFile> expectedMfs = new ArrayList<>();
    expectedMfs.add(vectors);
    expectedMfs.add(arrays);

    for (int i = 0; i < mfs.size(); i++) {
      assertEquals(expectedMfs.get(i).getFilename(), mfs.get(i).getFilename());
      assertEquals(expectedMfs.get(i).getContent(), mfs.get(i).getContent());
      assertEquals(expectedMfs.get(i).getDateCreated(), mfs.get(i).getDateCreated());
      assertEquals(expectedMfs.get(i).getDateModified(), mfs.get(i).getDateModified());
    }

  }

  /**
   * tests sort based on CREATED organizational flag
   *
   * @throws IOException exception for createMarkdown
   */
  @Test
  public void testSortByCreated() throws IOException, InterruptedException {

    Path file1 = Files.createTempFile("file1", ".md");
    Thread.sleep(10);
    Path file2 = Files.createTempFile("file2", ".md");
    MarkdownFile arrays = createMarkdown(file1);
    MarkdownFile vectors = createMarkdown(file2);
    ArrayList<MarkdownFile> mfs = new ArrayList<>();
    mfs.add(vectors);
    mfs.add(arrays);

    Utils.sort(OrganizationalFlag.CREATED, mfs);

    ArrayList<MarkdownFile> expectedMfs = new ArrayList<>();
    expectedMfs.add(arrays);
    expectedMfs.add(vectors);

    for (int i = 0; i < mfs.size(); i++) {
      assertEquals(expectedMfs.get(i).getFilename(), mfs.get(i).getFilename());
    }


  }


  /**
   * test select controller
   */
  @Test
  public void testSelectController() {
    // Test no arguments
    String[] noArguments = {};
    Controller result1 = Utils.selectController(noArguments);
    assertTrue(result1 instanceof StudySessionController);

    // Test three arguments
    String[] threeArguments = {"arg1", "arg2", "arg3"};
    Controller result2 = Utils.selectController(threeArguments);
    assertTrue(result2 instanceof FileCreationController);

    // Test invalid number of arguments
    String[] invalidArguments = {"arg1", "arg2"};
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Utils.selectController(invalidArguments));
  }

  /**
   * test converting number
   */
  @Test
  public void testConvertNum() {
    // Test valid number
    String validNumber = "123";
    int result1 = Utils.convertNum(validNumber);
    assertEquals(123, result1);

    // Test invalid number
    String invalidNumber = "abc";
    assertThrows(IllegalArgumentException.class, () -> Utils.convertNum(invalidNumber));
  }

  /**
   * test get content of file
   *
   * @throws IOException if not writable
   */
  @Test
  public void testGetContent() throws IOException {
    // Create a temporary file
    Path tempFile = Files.createTempFile("test", ".txt");
    String content = "Test content";
    Files.write(tempFile, content.getBytes());

    // Test reading content from the temporary file
    String result = Utils.getContent(tempFile);
    Assertions.assertEquals(content, result);

    // Delete the temporary file
    Files.deleteIfExists(tempFile);

    // Test reading content from a non-existing file
    Assertions.assertThrows(IllegalStateException.class, () -> Utils.getContent(tempFile));
  }

  /**
   * test write to file
   *
   * @throws IOException if not writeable
   */
  @Test
  public void testWriteToFile() throws IOException {
    // Create a temporary file
    Path tempFile = Files.createTempFile("test", ".txt");
    String content = "Test content";

    // Test writing content to the temporary file
    StringBuilder contentBuilder = new StringBuilder(content);
    Utils.writeToFile(contentBuilder, tempFile.toString());

    // Read the content from the temporary file
    String result = new String(Files.readAllBytes(tempFile));
    Assertions.assertEquals(content, result);

    // Delete the temporary file
    Files.deleteIfExists(tempFile);
  }


  /**
   * helper to create markdown file
   *
   * @param path given path
   * @return Markdown file
   * @throws IOException throws exception for readAttributes
   */
  private MarkdownFile createMarkdown(Path path) throws IOException {
    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
    return new MarkdownFile(path, path.getFileName().toString(), attr.creationTime(),
        attr.lastModifiedTime());
  }
}