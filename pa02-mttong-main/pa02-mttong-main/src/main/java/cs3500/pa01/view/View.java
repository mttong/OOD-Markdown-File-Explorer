package cs3500.pa01.view;

import java.io.IOException;

/**
 * View interface
 */
public interface View {

  /**
   * welcome method, welcomes user
   *
   * @return String
   * @throws IOException if not appendable
   */
  String getFile() throws IOException;

  /**
   * get numbers from user
   *
   * @param s any prompt
   * @return String
   * @throws IOException if not appendable
   */
  String getNumber(String s) throws IOException;


  /**
   * prints stats to user
   *
   * @param questionsAnswered  number of questions answered
   * @param hardToEasy number of questions changed from hard to easy
   * @param easyToHard number of questions changed from easy to hard
   * @param numHard    number of hard questions
   * @param numEasy    number of easy questions
   * @throws IOException if not appendable
   */
  void printStats(int questionsAnswered, int hardToEasy, int easyToHard, int numHard, int numEasy)
      throws IOException;

  /**
   * Prints text to user
   *
   * @param input prompt
   * @throws IOException if not appendable
   */
  void printInput(String input) throws IOException;

}
