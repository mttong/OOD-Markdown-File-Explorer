package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa01.view.StudySessionView;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * StudySessionView test
 */
public class StudySessionViewTest {
  Appendable append;
  Readable read;
  StudySessionView view;

  /**
   * setUp with readable and appendable
   */
  @BeforeEach
  public void setUp() {
    read = new StringReader("hello\nworld");
    append = new StringBuilder();
    view = new StudySessionView(read, append);
  }

  /**
   * test input
   */
  @Test
  public void testPrintInput() {
    String input = "hello";

    try {
      view.printInput(input);
    } catch (IOException e) {
      fail();
    }

    assertEquals("hello", this.append.toString());
  }

  /**
   * test get number
   *
   * @throws IOException if not appendable
   */
  @Test
  public void testGetNumber() throws IOException {
    String input = "hello";

    try {
      view.getNumber(input);
    } catch (IOException e) {
      fail();
    }

    assertEquals("hello", this.append.toString());
    assertEquals("world", view.getNumber("bye"));
  }

  /**
   * test print stats
   */
  @Test
  public void testPrintStats() {
    int questionsAnswered = 15;
    int hardToEasy = 10;
    int easyToHard = 2;
    int numHard = 20;
    int numEasy = 10;

    try {
      view.printStats(questionsAnswered, hardToEasy, easyToHard, numHard, numEasy);
    } catch (IOException e) {
      fail();
    }

    assertEquals("""
        Total number of questions answered: 15
        Total number of questions changed from easy to hard: 10
        Total number of questions changed from hard to easy: 2
        Updated total number of hard questions in question bank: 20
        Updated total number of easy questions in question bank: 10""", this.append.toString());
  }

  /**
   * test get file
   *
   * @throws IOException if not appendable
   */
  @Test
  public void testGetFile() throws IOException {

    try {
      view.getFile();
    } catch (IOException e) {
      fail();
    }

    assertEquals("Welcome to your study session! Please input a full path to a .sr \n",
        this.append.toString());
    assertEquals("world", view.getFile());
  }
}
