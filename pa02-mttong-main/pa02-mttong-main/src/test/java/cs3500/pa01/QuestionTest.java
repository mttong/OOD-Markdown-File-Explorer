package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Question class test
 */
public class QuestionTest {

  Question question;

  /**
   * creating question
   */
  @BeforeEach
  public void setUp() {
    question = new Question("What is the capital of France?", "Paris", Difficulty.HARD);
  }

  /**
   * test getQuestion
   */
  @Test
  public void testGetQuestion() {

    String result = question.getQuestion();

    assertEquals("What is the capital of France?", result);
  }

  /**
   * test getAnswer
   */
  @Test
  public void testGetAnswer() {

    String result = question.getAnswer();

    assertEquals("Paris", result);
  }

  /**
   * test getDifficulty
   */
  @Test
  public void testGetDifficulty() {

    Difficulty result = question.getDifficulty();

    assertEquals(Difficulty.HARD, result);
  }

  /**
   * test setDifficulty
   */
  @Test
  public void testSetDifficulty() {

    question.setDifficulty(Difficulty.EASY);

    Difficulty result = question.getDifficulty();

    assertEquals(Difficulty.EASY, result);
  }

  /**
   * test compareTo
   */
  @Test
  public void testCompareTo() {
    Question question1 = new Question("Question 1", "Answer 1", Difficulty.HARD);
    Question question2 = new Question("Question 2", "Answer 2", Difficulty.EASY);
    Question question3 = new Question("Question 1", "Answer 1", Difficulty.HARD);

    int result1 = question1.compareTo(question2);
    int result2 = question2.compareTo(question1);
    int result3 = question3.compareTo(question1);


    assertEquals(-1, result1);
    assertEquals(1, result2);
    assertEquals(0, result3);
  }

  /**
   * test toString
   */
  @Test
  public void testToString() {

    String result = question.toString();

    assertEquals("HARD- What is the capital of France?:::Paris\n", result);
  }
}
