package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa01.enums.Difficulty;
import cs3500.pa01.model.StudySessionModel;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * StudySessionModel test
 */
public class StudySessionModelTest {
  private StudySessionModel model;

  /**
   * creating path and amount of questions
   */
  public void setUp() {
    // Create a temporary file for testing
    Path file = Path.of("src", "test", "resources", "SampleFileInputs", "simple.sr");
    int numQuestions = 10;
    model = new StudySessionModel();
    model.loadQuestions(file, numQuestions);
  }

  /**
   * test getStudyQuestions
   */
  @Test
  public void testGetStudyQuestions() {
    setUp();
    ArrayList<Question> studyQuestions = model.getStudyQuestions();

    // Assert that the returned studyQuestions list has the correct size
    assertEquals(10, studyQuestions.size());

    // Assert that the studyQuestions list is shuffled
    ArrayList<Question> sortedQuestionsBank = new ArrayList<>(model.getQuestionsBank());
    sortedQuestionsBank.sort(Question::compareTo);
    assertNotEquals(sortedQuestionsBank, studyQuestions);
  }

  /**
   * test updateStats
   */
  @Test
  public void testUpdateStats() {
    setUp();
    assertEquals(0, model.getNumQuestionsAnswered());
    assertEquals(10, model.getNumHard());
    assertEquals(1, model.getNumEasy());

    // Test updating stats from HARD to EASY
    model.updateStats(Difficulty.HARD, Difficulty.EASY);
    assertEquals(1, model.getHardToEasy());
    assertEquals(9, model.getNumHard());
    assertEquals(2, model.getNumEasy());
    assertEquals(1, model.getNumQuestionsAnswered());

    // Test updating stats from EASY to HARD
    model.updateStats(Difficulty.EASY, Difficulty.HARD);
    assertEquals(1, model.getEasyToHard());
    assertEquals(10, model.getNumHard());
    assertEquals(1, model.getNumEasy());
    assertEquals(2, model.getNumQuestionsAnswered());

    // Test updating stats with no change in difficulty
    model.updateStats(Difficulty.EASY, Difficulty.EASY);
    assertEquals(1, model.getEasyToHard());
    assertEquals(10, model.getNumHard());
    assertEquals(1, model.getNumEasy());
    assertEquals(3, model.getNumQuestionsAnswered());

    model.updateStats(Difficulty.HARD, Difficulty.HARD);
    assertEquals(1, model.getEasyToHard());
    assertEquals(10, model.getNumHard());
    assertEquals(1, model.getNumEasy());
    assertEquals(4, model.getNumQuestionsAnswered());
  }

  /**
   * set up with more questions than in file
   */
  public void setUp2() {
    // Create a temporary file for testing
    Path file = Path.of("src", "test", "resources", "SampleFileInputs", "simple.sr");
    int numQuestions = 25;
    model = new StudySessionModel();
    model.loadQuestions(file, numQuestions);
  }

  /**
   * get second set
   */
  @Test
  public void testGetStudyQuestions2() {
    setUp2();
    ArrayList<Question> studyQuestions = model.getStudyQuestions();

    // Assert that the returned studyQuestions list has the correct size
    assertEquals(11, studyQuestions.size());


  }

}
