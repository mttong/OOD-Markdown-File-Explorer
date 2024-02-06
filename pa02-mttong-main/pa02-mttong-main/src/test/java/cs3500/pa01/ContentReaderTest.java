package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * reads the content of a MarkdownFile and returns the content as a String builder
 */
class ContentReaderTest {
  private static final String TEST_FILEPATH = "test.md";
  private static final String TEST_CONTENT = """
      # Heading 1
      [[Link 1]]
      Line 1 hello [[Me
       tired]] Line 2
      [[Link 2]] just doing [[More
       testing!!
       hooray]]
       [[Hello ::: Goodbye]]
      # Heading 2
      """;

  private ContentReader contentReader;

  /**
   * setting the markdown file before each test
   *
   * @throws IOException exception for getLastModifiedTime
   */
  @BeforeEach
  void setUp() throws IOException {
    ArrayList<MarkdownFile> mdlst = new ArrayList<>();
    Path testFilePath = Files.createTempFile(TEST_FILEPATH, null);
    Files.write(testFilePath, TEST_CONTENT.getBytes());
    FileTime dateModified = Files.getLastModifiedTime(testFilePath);
    FileTime dateCreated =
        Files.readAttributes(testFilePath, BasicFileAttributes.class).creationTime();
    MarkdownFile markdownFile =
        new MarkdownFile(testFilePath, TEST_FILEPATH, dateModified, dateCreated);
    mdlst.add(markdownFile);
    contentReader = new ContentReader(mdlst);
  }


  /**
   * test to see if file was parsed correctly
   */
  @Test
  void testGetContent() {
    String expectedParsedContent = """
        # Heading 1
        - Link 1
        - Me tired
        - Link 2
        - More testing!! hooray
        # Heading 2
        """;
    String expectedParsedQuestion = """
        HARD- Hello ::: Goodbye
        """;

    StringBuilder parsedContent = contentReader.getContent();
    StringBuilder parsedQuestion = contentReader.getQuestions();
    System.out.print(parsedQuestion.toString());

    assertEquals(expectedParsedContent, parsedContent.toString(), "Parsed content should match");
    assertEquals(expectedParsedQuestion, parsedQuestion.toString(), "Parsed content should match");
  }
}
