package cs3500.pa01.view;

import java.io.IOException;
import java.util.Scanner;

/**
 * view for study session
 */
public class StudySessionView implements View {
  Scanner scanner;
  Appendable append;

  /**
   * constructor
   *
   * @param read   readable
   * @param append scanner for System.in
   */
  public StudySessionView(Readable read, Appendable append) {
    scanner = new Scanner(read);
    this.append = append;
  }

  /**
   * welcome user and get sr file
   *
   * @return String
   * @throws IOException if not appendable
   */
  public String getFile() throws IOException {
    this.append.append("Welcome to your study session! Please input a full path to a .sr \n");
    return scanner.nextLine();
  }

  /**
   * get number from user
   *
   * @param s any prompt
   * @return String
   * @throws IOException if not appendable
   */
  public String getNumber(String s) throws IOException {
    this.append.append(s);
    return scanner.nextLine();
  }

  /**
   * prints stats when user is done with study session
   *
   * @param questionsAnswered  questions answered
   * @param hardToEasy questions changed from hard to easy
   * @param easyToHard questions changed from easy to hard
   * @param numHard    questions in bank that are now hard
   * @param numEasy    questions in bank that are now easy
   * @throws IOException if not appendable
   */
  public void printStats(int questionsAnswered, int hardToEasy, int easyToHard, int numHard,
                         int numEasy)
      throws IOException {
    this.append.append("Total number of questions answered: ")
        .append(String.valueOf(questionsAnswered))
        .append("\nTotal number of questions changed from easy to hard: ")
        .append(String.valueOf(hardToEasy))
        .append("\nTotal number of questions changed from hard to easy: ")
        .append(String.valueOf(easyToHard))
        .append("\nUpdated total number of hard questions in question bank: ")
        .append(String.valueOf(numHard))
        .append("\nUpdated total number of easy questions in question bank: ")
        .append(String.valueOf(numEasy));
  }

  /**
   * prints input to user
   *
   * @param input prompt
   * @throws IOException if not appendable
   */
  public void printInput(String input) throws IOException {
    this.append.append(input);
  }

}
