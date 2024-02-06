package cs3500.pa01.model;

import cs3500.pa01.Question;
import cs3500.pa01.enums.Difficulty;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Model interface
 */
public interface Model {

  /**
   * loads questions from sr file
   *
   * @param srfile Path to sr file
   * @param num     amount of questions to practice
   */
  void loadQuestions(Path srfile, int num);

  /**
   * gets the study questions
   *
   * @return a list of Questions
   */
  ArrayList<Question> getStudyQuestions();

  /**
   * updates the statistics
   *
   * @param prev Difficulty
   * @param now  Difficulty
   */
  void updateStats(Difficulty prev, Difficulty now);

  /**
   * gets the number of questions answered in study session
   *
   * @return int
   */
  int getNumQuestionsAnswered();

  /**
   * gets the number of questions changed from hard to easy in study session
   *
   * @return int
   */
  int getHardToEasy();

  /**
   * gets the number of questions changed from easy to hard in study session
   *
   * @return int
   */
  int getEasyToHard();

  /**
   * gets the number of hard questions in bank
   *
   * @return int
   */
  int getNumHard();

  /**
   * gets the number of easy questions in bank
   *
   * @return int
   */
  int getNumEasy();

  /**
   * gets the question bank
   *
   * @return a list of questions
   */
  ArrayList<Question> getQuestionsBank();
}
