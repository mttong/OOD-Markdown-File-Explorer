package cs3500.pa01.model;

import cs3500.pa01.Question;
import cs3500.pa01.enums.Difficulty;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Mock model for testing controller
 */
public class MockModel implements Model {
  public StringBuilder log; //log which methods are run
  public ArrayList<Question> tester;

  /**
   * constructor
   */
  public MockModel() {
    this.log = new StringBuilder();
    this.tester = new ArrayList<>();
    this.tester.add(new Question("hello", "goodbye", Difficulty.EASY));
  }

  /**
   * mock load questions
   *
   * @param srfile Path to sr file
   * @param num     amount of questions to practice
   */
  @Override
  public void loadQuestions(Path srfile, int num) {
    this.log.append("loadQuestions called.");
  }

  /**
   * mock getStudyQuestions
   *
   * @return test list
   */
  @Override
  public ArrayList<Question> getStudyQuestions() {
    this.log.append("getStudyQuestions called.");
    return this.tester;
  }

  /**
   * mock update stats
   *
   * @param prev Difficulty
   * @param now  Difficulty
   */
  @Override
  public void updateStats(Difficulty prev, Difficulty now) {
    this.log.append("updateStats called.");
  }

  /**
   * mock get number questions answered
   *
   * @return int
   */
  @Override
  public int getNumQuestionsAnswered() {
    this.log.append("getAnswered called.");
    return 0;
  }

  /**
   * mock get number of hard to easy
   *
   * @return int
   */
  @Override
  public int getHardToEasy() {
    this.log.append("getHardToEasy called.");
    return 0;
  }

  /**
   * mock get number of easy to hard
   *
   * @return int
   */
  @Override
  public int getEasyToHard() {
    this.log.append("getEasyToHard called.");
    return 0;
  }

  /**
   * mock get number of hard
   *
   * @return int
   */
  @Override
  public int getNumHard() {
    this.log.append("getNumHard called.");
    return 0;
  }

  /**
   * mock get number of easy
   *
   * @return int
   */
  @Override
  public int getNumEasy() {
    this.log.append("getNumEasy called.");
    return 0;
  }

  /**
   * mock get question bank
   *
   * @return test list
   */
  @Override
  public ArrayList<Question> getQuestionsBank() {
    this.log.append("getQuestionsBank called.");
    return this.tester;
  }
}
